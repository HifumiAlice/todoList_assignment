package com.teamsparta.todolist.domain.member.adapter

import com.teamsparta.todolist.domain.member.dto.parameter.UserDetailsParameter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class MemberDetails(
    val id : Long,
    val account: String,
    private val pw: String,
    private val role: String
): User(account, pw, arrayListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_${role}"))) {

    constructor(param: UserDetailsParameter) : this (
        id = param.id,
        account = param.account,
        pw = param.password,
        role = param.role
    )
}