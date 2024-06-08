package com.teamsparta.todolist.domain.member.repository

import com.teamsparta.todolist.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByAccount(account: String): Boolean

    @Query("select m from Member m where m.id = :id")
    fun findByIdOne(id : Long): Member
}
