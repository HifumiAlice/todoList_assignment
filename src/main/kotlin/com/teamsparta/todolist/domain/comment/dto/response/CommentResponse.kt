package com.teamsparta.todolist.domain.comment.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val comment: String,
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime,
    val member: MemberResponse
)
