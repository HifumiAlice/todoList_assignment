package com.teamsparta.todolist.domain.todo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime


data class TodoResponse (
    val id : Long,
    val title : String,
    val content : String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val date : LocalDateTime,
    val writer : String
)
