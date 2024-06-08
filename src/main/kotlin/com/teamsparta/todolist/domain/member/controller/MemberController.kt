package com.teamsparta.todolist.domain.member.controller

import com.teamsparta.todolist.domain.member.dto.request.MemberCreateRequest
import com.teamsparta.todolist.domain.member.dto.response.MemberResponse
import com.teamsparta.todolist.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signups")
    fun signup(@RequestBody request: MemberCreateRequest): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signUp(request))
    }
}