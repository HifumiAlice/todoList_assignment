package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import com.teamsparta.todolist.domain.member.adapter.MemberDetails

interface CommentService {

    fun createComment(id: Long, request: CommentCreateRequest, memberDetails: MemberDetails?): CommentResponse

    fun updateComment(
        id: Long,
        commentId: Long,
        request: CommentUpdateRequest,
        memberDetails: MemberDetails?
    ): CommentResponse

    fun deleteComment(id: Long, commentId: Long, memberDetails: MemberDetails?): Unit
}