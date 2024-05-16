package com.teamsparta.todolist.domain.comment.dto

data class CommentCreateRequest (
    val comment : String,
    val commentWriter : String,
    val password : String
)
