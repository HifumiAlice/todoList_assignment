package com.teamsparta.todolist.domain.todo.model

import com.teamsparta.todolist.domain.comment.model.Comment
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="todo")
class Todo (
    @Column(name="title", nullable = false)
    var title : String = "",

    @Column(name = "content" , nullable = false)
    var content : String = "",

    @Column(name="date", nullable = false)
    var date : LocalDateTime = LocalDateTime.now(),

    @Column(name = "writer", nullable = false)
    var writer : String = "",

    @Column(name = "achievement", nullable = false)
    var achievement : Boolean = false,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "todo_id", nullable = false)
    var comments : MutableList<Comment> = mutableListOf()
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null

    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        this.comments.remove(comment)
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