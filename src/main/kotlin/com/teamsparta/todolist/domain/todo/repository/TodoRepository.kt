package com.teamsparta.todolist.domain.todo.repository

import com.teamsparta.todolist.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findAllByOrderByDateDesc(): MutableList<Todo>
    fun findAllByOrderByDateAsc(): MutableList<Todo>
    fun findAllByWriterOrderByDateDesc(writer: String): MutableList<Todo>
    fun findAllByWriterOrderByDateAsc(writer: String): MutableList<Todo>
}