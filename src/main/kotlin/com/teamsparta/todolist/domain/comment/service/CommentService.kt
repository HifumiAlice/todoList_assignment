package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentDeleteRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse

interface CommentService {

    fun createComment(id: Long, request: CommentCreateRequest, memberId: Long): CommentResponse

    fun updateComment(id: Long, commentId: Long, request: CommentUpdateRequest, memberId: Long): CommentResponse

    fun deleteComment(id: Long, commentId: Long, memberId: Long): Unit
}