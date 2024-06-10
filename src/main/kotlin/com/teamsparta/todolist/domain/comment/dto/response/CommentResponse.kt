package com.teamsparta.todolist.domain.comment.dto.response

import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val comment: String,
    val createdAt: LocalDateTime,
//    val todoId : Long,
    val member: MemberResponse // TODO : 멤버로할지 멤버 아이디로 할지 나중에 선택할 것
)
