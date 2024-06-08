package com.teamsparta.todolist.domain.todo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import java.time.LocalDateTime


data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime,
    val memberId: Long,
    val achievement: Boolean = false,
    val comments: List<CommentResponse> = listOf()
)
