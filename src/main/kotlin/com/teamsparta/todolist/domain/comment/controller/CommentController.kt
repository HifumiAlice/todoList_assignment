package com.teamsparta.todolist.domain.comment.controller

import com.teamsparta.todolist.domain.comment.dto.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.CommentResponse
import com.teamsparta.todolist.domain.comment.dto.CommentUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos/{id}/")
@RestController
class CommentController {

    @PostMapping()
    fun createComment(@PathVariable id : Long, @RequestBody request : CommentCreateRequest) : ResponseEntity<CommentResponse> {
        TODO("Not yet Implemented")
    }

    @PutMapping("/{comment-id}")
    fun updateComment(@PathVariable id : Long, @PathVariable(name = "comment-id") commentId : Long,
                      @RequestBody request : CommentUpdateRequest)
    : ResponseEntity<CommentResponse> {
        TODO("Not yet Implemented")
    }

    @DeleteMapping("/{comment-id}")
    fun deleteComment(@PathVariable id : Long, @PathVariable(name = "comment-id") commentId : Long) : ResponseEntity<Unit> {
        TODO("Not yet Implemented")
    }

}