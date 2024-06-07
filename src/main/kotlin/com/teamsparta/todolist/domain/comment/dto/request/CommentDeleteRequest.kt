package com.teamsparta.todolist.domain.comment.dto.request

data class CommentDeleteRequest(
    val writer: String,
    val password: String
)
