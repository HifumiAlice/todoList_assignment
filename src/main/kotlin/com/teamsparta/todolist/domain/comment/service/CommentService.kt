package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentDeleteRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse

interface CommentService {

    fun createComment(id: Long, request: CommentCreateRequest): CommentResponse

    fun updateComment(id: Long, commentId: Long, request: CommentUpdateRequest): CommentResponse

    fun deleteComment(id: Long, commentId: Long, request: CommentDeleteRequest): Unit
}