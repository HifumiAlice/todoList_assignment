package com.teamsparta.todolist.domain.comment.model

import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.model.toResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "comment", nullable = false)
    var comment: String,

    @Column(name = "member_id", nullable = false)
    val memberId: Long,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(member: Member): CommentResponse {
    return CommentResponse(
        id = id!!,
        comment = comment,
        createdAt = createdAt,
//        todoId = todoId,
        member = member.toResponse()
    )
}


