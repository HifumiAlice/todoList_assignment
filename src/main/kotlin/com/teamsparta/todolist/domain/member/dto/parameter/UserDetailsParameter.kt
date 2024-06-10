package com.teamsparta.todolist.domain.member.dto.parameter

data class UserDetailsParameter(
    val id: Long,
    val account: String,
    val password: String,
    val role: String
)