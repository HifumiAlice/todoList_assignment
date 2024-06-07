package com.teamsparta.todolist.domain.todo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class TodoCreateRequest (
    val title : String,
    val content : String,
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    val date : LocalDateTime = LocalDateTime.now(),
    val writer : String
)
