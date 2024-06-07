package com.teamsparta.todolist.domain.comment.dto.response

data class CommentResponse(
    val id: Long,
    val comment: String,
    val commentWriter: String
)
