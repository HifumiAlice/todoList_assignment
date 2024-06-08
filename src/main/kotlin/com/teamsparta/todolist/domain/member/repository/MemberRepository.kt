package com.teamsparta.todolist.domain.member.repository

import com.teamsparta.todolist.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByAccount(account: String): Boolean

}
