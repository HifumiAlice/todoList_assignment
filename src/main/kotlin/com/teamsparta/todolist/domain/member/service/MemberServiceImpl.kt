package com.teamsparta.todolist.domain.member.service

import com.teamsparta.todolist.domain.exception.AlreadyRegisterAccountException
import com.teamsparta.todolist.domain.member.dto.request.LoginRequest
import com.teamsparta.todolist.domain.member.dto.request.MemberSignUpRequest
import com.teamsparta.todolist.domain.member.dto.response.LoginResponse
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.model.toResponse
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import com.teamsparta.todolist.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : MemberService {


    override fun signUp(request: MemberSignUpRequest): MemberResponse {
        /* TODO : 나중에 할것
            계정을 이메일로만 받기 및 비밀번호 제한할 것
         */
        if (memberRepository.existsByAccount(request.account)) {
            throw AlreadyRegisterAccountException()
        }

        val member: Member = Member(
            account = request.account,
            password = passwordEncoder.encode(request.password),
            role = "USER"
        )
        memberRepository.save(member)
        return member.toResponse()
    }


    override fun login(request: LoginRequest): LoginResponse {
        val member: Member = memberRepository.findByAccount(request.account)
            ?: throw IllegalArgumentException("Not found account : ${request.account}")

        if (!passwordEncoder.matches(request.password, member.password)) {
            throw IllegalArgumentException("The password is different")
        }

        return LoginResponse(jwtPlugin.generateAccessToken(member.id.toString(),member.account,member.role))
    }
}



