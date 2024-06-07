package com.teamsparta.todolist.domain.todo.dto

data class TodoUpdateRequest (
    val title : String,
    val content : String,
    val writer : String
)