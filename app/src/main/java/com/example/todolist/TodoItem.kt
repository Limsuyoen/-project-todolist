package com.example.todolist


data class TodoItem(
    var text: String,
    var isChecked: Boolean,
    var isEditing: Boolean,
    var isNew: Boolean,

)

