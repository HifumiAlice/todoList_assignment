package com.teamsparta.todolist.domain.todo.model

import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.model.toResponse
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class Todo(
    @Column(name = "title", nullable = false)
    var title: String = "",

    @Column(name = "content", nullable = false)
    var content: String = "",

    @Column(name = "createdat", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "achievement", nullable = false)
    var achievement: Boolean = false,

    @Column(name = "member_id", nullable = false)
    var memberId: Long

//    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "todo_id", nullable = false)
//    var comments: MutableList<Comment> = mutableListOf()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

//    fun addComment(comment: Comment) {
//        this.comments.add(comment)
//    }
//
//    fun removeComment(comment: Comment) {
//        this.comments.remove(comment)
//    }

}

fun Todo.toResponse(isAll: Boolean = true, member: Member): TodoResponse {
    when (isAll) {
        true -> {
            return TodoResponse(
                id = id!!,
                title = title,
                content = content,
                member = member.toResponse(),
                createdAt = createdAt,
                achievement = achievement
            )
        }

        false -> {
            return TodoResponse(
                id = id!!,
                title = title,
                content = content,
                member = member.toResponse(),
                createdAt = createdAt,
                achievement = achievement,
//                comments = comments.map { it.toResponse() }.toList()
            )
        }
    }

}