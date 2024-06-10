package com.teamsparta.todolist.domain.todo.repository

import com.teamsparta.todolist.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findAllByOrderByCreatedAtDesc(): MutableList<Todo>
    fun findAllByOrderByCreatedAtAsc(): MutableList<Todo>
    fun findAllByMemberIdOrderByCreatedAtDesc(memberId: Long): MutableList<Todo>
    fun findAllByMemberIdOrderByCreatedAtAsc(memberId: Long): MutableList<Todo>
}