package com.teamsparta.todolist.domain.comment.dto

data class CommentResponse (
    val id : Long,
    val comment : String,
    val commentWriter : String
)
