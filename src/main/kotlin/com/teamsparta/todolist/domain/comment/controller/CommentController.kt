package com.teamsparta.todolist.domain.comment.controller

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentDeleteRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos/{id}")
@RestController
class CommentController(private val commentService: CommentService) {

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    fun createComment(
        @PathVariable id: Long,
        @RequestBody request: CommentCreateRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(id, request))
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{comment-id}")
    fun updateComment(
        @PathVariable id: Long, @PathVariable(name = "comment-id") commentId: Long,
        @RequestBody request: CommentUpdateRequest
    )
            : ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id, commentId, request))
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{comment-id}")
    fun deleteComment(
        @PathVariable id: Long, @PathVariable(name = "comment-id") commentId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(id, commentId))
    }

}