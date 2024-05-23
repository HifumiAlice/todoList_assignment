package com.teamsparta.todolist.domain.comment.dto

data class CommentUpdateRequest (
    val comment : String,
    val writer : String,
    val password : String
)
