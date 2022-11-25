package com.minjaeheo.todolistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minjaeheo.todolistapp.databinding.ListItemTodoBinding
import com.minjaeheo.todolistapp.model.TodoInfo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var lstTodo : ArrayList<TodoInfo> = ArrayList()

    // 가장 먼저 호출되는 init
    init {
        // 리스트 아이템 인스턴스 생성
        val todoItem = TodoInfo()
        todoItem.todoContent = "컴퓨터 사용시간 줄이기"
        todoItem.todoDate = "2022-11-25 17:46"
        lstTodo.add(todoItem)

        val todoItem2 = TodoInfo()
        todoItem2.todoContent = "컴퓨터 사용시간 줄이기2"
        todoItem2.todoDate = "2022-11-25 17:47"
        lstTodo.add(todoItem2)

        val todoItem3 = TodoInfo()
        todoItem3.todoContent = "컴퓨터 사용시간 줄이기3"
        todoItem3.todoDate = "2022-11-25 17:48"
        lstTodo.add(todoItem3)
    }

    fun addListItem(todoItem: TodoInfo) {
        // add는 0, 1, 2, 3, 4 배열의 뒷 순서로 배치 add에 몇 번째 순서로 넣을 건지 정할 수 있다.
        lstTodo.add(0, todoItem)
    }

    class TodoViewHolder(private val binding: ListItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoInfo) {
            // 리스트 뷰 데이터를 UI에 연동
            binding.tvContent.setText(todoItem.todoContent)
            binding.tvDate.setText(todoItem.todoDate)

            // 리스트 삭제 버튼 클릭 연동
            binding.btnRemove.setOnClickListener {
                // 쓰레기통 이미지 클릭 시 내부 로직 수행

            }
        }
    }

    // 뷰홀더가 생성됨. (각 리스트 아이템 1개씩 구성될 때마다 오버라이드 메소드 호출.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val binding = ListItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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