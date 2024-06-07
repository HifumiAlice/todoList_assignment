package com.teamsparta.todolist.domain.comment.model

import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "comment", nullable = false)
    var comment: String,

    @Column(name = "writer", nullable = false)
    var writer: String,

    @Column(name = "password", nullable = false)
    var password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        comment = comment,
        commentWriter = writer
    )
}


