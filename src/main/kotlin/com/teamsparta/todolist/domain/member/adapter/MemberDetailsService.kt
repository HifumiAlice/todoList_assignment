package com.teamsparta.todolist.domain.member.adapter

import com.teamsparta.todolist.domain.member.model.toUserDetailsParameter
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val (id, account) = username.split(":")
        val member = memberRepository.findByIdAndAccount(id.toLong(), account)
            ?: throw UsernameNotFoundException("Member not found with id: ${id}")
        return MemberDetails(member.toUserDetailsParameter())
    }
}