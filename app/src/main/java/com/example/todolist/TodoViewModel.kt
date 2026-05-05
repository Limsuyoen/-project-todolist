package com.example.todolist

import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    //dataset을 날짜별로 묶기
    val todoMap = mutableMapOf<String, MutableList<TodoItem>>()

    fun getList(data: Int): MutableList<TodoItem> {
        return todoMap.getOrPut(data){
            mutableListOf(TodoItem("",false,true,true,"0000-00-00"))
        }
    }
}