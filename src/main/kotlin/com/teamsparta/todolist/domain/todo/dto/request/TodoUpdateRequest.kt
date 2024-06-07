package com.teamsparta.todolist.domain.todo.dto.request

data class TodoUpdateRequest(
    val title: String,
    val content: String,
    val writer: String
)