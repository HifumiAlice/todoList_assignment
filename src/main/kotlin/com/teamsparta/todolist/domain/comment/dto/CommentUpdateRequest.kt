package com.teamsparta.todolist.domain.comment.dto

data class CommentUpdateRequest (
    val comment : String,
    val commentWriter : String,
    val password : String
)
