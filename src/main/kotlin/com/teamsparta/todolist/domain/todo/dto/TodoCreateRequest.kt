package com.teamsparta.todolist.domain.todo.dto

data class TodoCreateRequest (
    val title : String,
    val content : String,
    val date : String,
    val writer : String
)
