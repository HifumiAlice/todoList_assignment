package com.teamsparta.todolist.domain.todo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.teamsparta.todolist.domain.comment.dto.CommentResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime


data class TodoResponse (
    val id : Long,
    val title : String,
    val content : String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val date : LocalDateTime,
    val writer : String,
    val achievement : Boolean = false,
    val comments : List<CommentResponse> = listOf()
)
