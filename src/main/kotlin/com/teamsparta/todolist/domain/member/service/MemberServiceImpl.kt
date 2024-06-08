package com.teamsparta.todolist.domain.member.service

import com.teamsparta.todolist.domain.exception.AlreadyMemberException
import com.teamsparta.todolist.domain.member.dto.request.MemberCreateRequest
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.model.toResponse
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    override fun signUp(request: MemberCreateRequest): MemberResponse {
        // TODO : 계정 생성할 때 이미 존재하는 계정인지 확인
        // TODO : 존재하지 않는다면 계정 생성 가능
        // TODO : 계정 생성할 때 기본적으로 역할은 USER로 생성 --> 추후에 추가될지도?

        if (memberRepository.existsByAccount(request.account)) throw AlreadyMemberException()

        val member: Member = Member(
            account = request.account,
            password = request.password
        )

        memberRepository.save(member)

        return member.toResponse()


    }

}