package com.teamsparta.todolist.domain.todo.dto.request

data class TodoCreateRequest(
    val title: String,
    val content: String,
    val memberId: Long
)
