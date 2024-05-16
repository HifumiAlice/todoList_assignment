package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.CommentResponse
import com.teamsparta.todolist.domain.comment.dto.CommentUpdateRequest
import org.springframework.stereotype.Service

@Service
class CommentServiceImplement : CommentService {
    override fun createComment(id: Long, request: CommentCreateRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun updateComment(id: Long,commentId : Long, request: CommentUpdateRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(id: Long, commentId : Long) : Unit{
        TODO("Not yet implemented")
    }
}