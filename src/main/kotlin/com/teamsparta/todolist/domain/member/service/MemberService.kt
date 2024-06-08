package com.teamsparta.todolist.domain.member.service

import com.teamsparta.todolist.domain.member.dto.request.MemberCreateRequest
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse

interface MemberService {
    fun signUp(request: MemberCreateRequest): MemberResponse
}
