package com.minjaeheo.todolistapp.adapter

import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.minjaeheo.todolistapp.database.TodoDatabase
import com.minjaeheo.todolistapp.databinding.DialogEditBinding
import com.minjaeheo.todolistapp.databinding.ListItemTodoBinding
import com.minjaeheo.todolistapp.model.TodoInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var lstTodo : ArrayList<TodoInfo> = ArrayList()
    private lateinit var roomDatabase: TodoDatabase

    fun addListItem(todoItem: TodoInfo) {
        // add는 0, 1, 2, 3, 4 배열의 뒷 순서로 배치 add에 몇 번째 순서로 넣을 건지 정할 수 있다.
        lstTodo.add(0, todoItem)
    }

    inner class TodoViewHolder(private val binding: ListItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoInfo) {
            // 리스트 뷰 데이터를 UI에 연동
            binding.tvContent.setText(todoItem.todoContent)
            binding.tvDate.setText(todoItem.todoDate)

            // 리스트 삭제 버튼 클릭 연동
            binding.btnRemove.setOnClickListener {
                // 쓰레기통 이미지 클릭 시 내부 로직 수행

                AlertDialog.Builder(binding.root.context)
                    .setTitle("[주의]")
                    .setMessage("삭제하시면 데이터는 복구되지 않아요.\n정말로 삭제하시겠어요?\n")
                    .setPositiveButton("제거", DialogInterface.OnClickListener { dialogInterface, i ->

                        CoroutineScope(Dispatchers.IO). launch {
                            val innerLstTodo = roomDatabase.todoDao().getAllReadDate()
                            for (item in innerLstTodo) {
                                if (item.todoContent == todoItem.todoContent && item.todoDate == todoItem.todoDate) {
                                    // delete to database item
                                    roomDatabase.todoDao().deleteTodoData(item)
                                }
                            }

                            // ui remove
                            lstTodo.remove(todoItem)
                            (binding.root.context as Activity). runOnUiThread {
                                notifyDataSetChanged() // 리스트 새로고침 }
                                Toast.makeText(binding.root.context, "작성한 To-Do가 제거되었어요.", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .show()
            }

            // 리스트 수정 클릭 연동
            binding.root.setOnClickListener{
                val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)
                bindingDialog.etMemo.setText(todoItem.todoContent)


                AlertDialog.Builder(binding.root.context)
                    .setTitle("To-Do 남기기")
                    .setView(bindingDialog.root)
                    .setPositiveButton("수정완료", DialogInterface.OnClickListener { dialogInterface, i ->
                        CoroutineScope(Dispatchers.IO). launch {
                            val innerLstTodo = roomDatabase.todoDao().getAllReadDate()
                            for (item in innerLstTodo) {
                                if (item.todoContent == todoItem.todoContent && item.todoDate == todoItem.todoDate) {
                                    // modify to database item
                                    item.todoContent = bindingDialog.etMemo.text.toString()
                                    item.todoDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                                    roomDatabase.todoDao().updateTodoData(item)
                                }
                            }
                            // ui modify
                            todoItem.todoContent = bindingDialog.etMemo.text.toString()
                            todoItem.todoDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                            // array list 수정
                            lstTodo.set(adapterPosition, todoItem)
                            (binding.root.context as Activity). runOnUiThread {
                                notifyDataSetChanged() // 리스트 새로고침 }
                            }
                        }
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .show()
            }
        }
    }

    // 뷰홀더가 생성됨. (각 리스트 아이템 1개씩 구성될 때마다 오버라이드 메소드 호출.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val binding = ListItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // 룸 데이터베이스 초기화
        roomDatabase = TodoDatabase.getInstance(binding.root.context)!!
        return TodoViewHolder(binding)
    }

    // 뷰홀더가 바인드 (결합) 이루어질 때 해줘야 될 처리들을 구현. position = 배열의 index
    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        holder.bind(lstTodo[position])
    }

    // 리스트 총 갯수
    override fun getItemCount(): Int {
        return lstTodo.size
    }


}