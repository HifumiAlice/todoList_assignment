package com.teamsparta.todolist.domain.todo.dto.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class TodoCreateRequest(
    val title: String,
    val content: String,    
    val memberId: Long // TODO : 나중에 인가나 인증에서 memberId를 알아서 찾아낼 것
)
