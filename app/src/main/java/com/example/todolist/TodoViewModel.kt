package com.example.todolist

import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    val todoMap = mutableMapOf<String, MutableList<TodoItem>>()

    fun getList(data: String): MutableList<TodoItem> {
        return todoMap.getOrPut(data){
            mutableListOf(TodoItem("",false,true,true))
        }
    }
}