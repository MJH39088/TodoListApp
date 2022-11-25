package com.minjaeheo.todolistapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.minjaeheo.todolistapp.adapter.TodoAdapter
import com.minjaeheo.todolistapp.databinding.ActivityListMainBinding
import com.minjaeheo.todolistapp.databinding.DialogEditBinding
import com.minjaeheo.todolistapp.databinding.ListItemTodoBinding
import com.minjaeheo.todolistapp.model.TodoInfo
import java.text.SimpleDateFormat
import java.util.Date

class ListMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터 인스턴스 생성
        todoAdapter = TodoAdapter()

        // 리사이클러뷰에 어댑터 세팅
        binding.rvTodo.adapter = todoAdapter

        // 작성하기 버튼 클릭
        binding.btnWrite.setOnClickListener {
            val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)

            AlertDialog.Builder(this)
                .setTitle("To-Do 남기기")
                .setView(bindingDialog.root)
                .setPositiveButton("작성완료", DialogInterface.OnClickListener { dialogInterface, i ->
                    val todoItem = TodoInfo()
                    todoItem.todoContent = bindingDialog.etMemo.text.toString()
                    todoItem.todoDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                    todoAdapter.addListItem(todoItem) // 어댑터 전역변수 arraylist 쪽에 아이템 추가하기 위한 메소드 호출
                    todoAdapter.notifyDataSetChanged() // 리스트(어댑터) 새로고침
                })
                .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .show()
        }
    }
}