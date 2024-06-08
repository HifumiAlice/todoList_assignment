package com.teamsparta.todolist.domain.member.model

import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(

    @Column(name = "account", nullable = false)
    val account: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "role", nullable = false)
    var role: String = "USER"

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Member.toResponse(): MemberResponse {
    return MemberResponse(
        id = id!!,
        account = account,
        role = role
    )
}
