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
    var id : Long? = null

    @Column(name="title", nullable = false)
    var title : String = ""

    @Column(name = "content" , nullable = false)
    var content : String = ""

    @Column(name="date", nullable = false)
    var date : LocalDateTime = LocalDateTime.now()

    @Column(name = "writer", nullable = false)
    var writer : String = ""

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
        writer = writer
    )
}