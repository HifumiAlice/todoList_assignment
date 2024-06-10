package com.teamsparta.todolist.domain.exception

data class AlreadyMemberException(
    override val message: String? = "Already in members"
) : RuntimeException()
