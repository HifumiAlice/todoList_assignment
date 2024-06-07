package com.teamsparta.todolist.domain.comment.dto.request

data class CommentUpdateRequest(
    val comment: String,
    val writer: String,
    val password: String
)
