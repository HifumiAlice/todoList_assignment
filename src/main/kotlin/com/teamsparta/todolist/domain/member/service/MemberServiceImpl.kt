package com.teamsparta.todolist.domain.member.service

import com.teamsparta.todolist.domain.member.dto.request.MemberSignUpRequest
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.model.toResponse
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    override fun signUp(request: MemberSignUpRequest): MemberResponse {
        /* TODO : 나중에 할것
            계정을 이메일로만 받기 및 비밀번호 제한할 것
         */
        val member : Member = Member(
            account = request.account,
            password = request.password,
            role = "user"
        )
        memberRepository.save(member)
        return memberToResponse(member)
//        return member.toResponse()
    }

    private fun memberToResponse(member: Member): MemberResponse {
        return MemberResponse(
            id = member.id!!,
            account = member.account,
            role = member.role
        )
    }
}



