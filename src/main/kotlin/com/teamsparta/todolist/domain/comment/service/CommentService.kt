package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.CommentResponse
import com.teamsparta.todolist.domain.comment.dto.CommentUpdateRequest

interface CommentService {

    fun createComment(id : Long, request : CommentCreateRequest) : CommentResponse

    fun updateComment(id : Long, commentId : Long,  request : CommentUpdateRequest) : CommentResponse

    fun deleteComment(id : Long, commentId : Long) : Unit
}