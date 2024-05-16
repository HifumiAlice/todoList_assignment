package com.teamsparta.todolist.domain.todo.model

import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="todo")
open class Todo {
    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id : Long? = null

    @Column(name="title", nullable = false)
    open var title : String = ""

    @Column(name = "content" , nullable = false)
    open var content : String = ""

    @Column(name="date", nullable = false)
    open var date : LocalDateTime = LocalDateTime.now()

    @Column(name = "writer", nullable = false)
    open var writer : String = ""

    open var achievement : Boolean = false

    constructor( title: String, content: String, date: LocalDateTime, writer: String) {
        this.title = title
        this.content = content
        this.date = date
        this.writer = writer
    }

}

fun Todo.toResponse() : TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        content = content,
        date = date,
        writer = writer,
        achievement = achievement
    )
}