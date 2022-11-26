package com.minjaeheo.todolistapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.minjaeheo.todolistapp.model.TodoInfo

@Dao
interface TodoDao {

    // database table 에 삽입 (추가)
    @Insert
    fun insertTodoData(todoInfo: TodoInfo)

    // database table 에 기존에 존재하는 데이터를 수정
    @Update
    fun updateTodoData(todoInfo: TodoInfo)

    // database table 에 기존에 존재하는 데이터를 삭제
    @Delete
    fun deleteTodoData(todoInfo: TodoInfo)

    // database table 에 전체 데이터를 가지고 옴. (조회)
    // TodoInfo의 정보를 다 가져온 뒤 todoDate를 기준
    @Query("SELECT * FROM TodoInfo ORDER BY todoDate")
    fun getAllReadDate(): List<TodoInfo>
}