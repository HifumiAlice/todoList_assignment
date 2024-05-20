package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.CommentDeleteRequest
import com.teamsparta.todolist.domain.comment.dto.CommentResponse
import com.teamsparta.todolist.domain.comment.dto.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.model.Comment
import com.teamsparta.todolist.domain.comment.model.toResponse
import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val todoRepository : TodoRepository,
    private val commentRepository : CommentRepository

) : CommentService {

    @Transactional
    override fun createComment(id: Long, request: CommentCreateRequest): CommentResponse {
        // TODO : todo의 id가 존재하는지 확인 없으면 ModelException
        // TODO : comment 생성
        // TODO : todoEntity에 Comment 추가

        val todo : Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        if (request.writer.isEmpty()) throw IllegalStateException("Please comment writer")
        if (request.password.isEmpty()) throw IllegalStateException("Password cannot be empty")

        val comment : Comment = Comment(
            comment = request.comment,
            password = request.password,
            writer = request.writer
        )

        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(id: Long,commentId : Long, request: CommentUpdateRequest): CommentResponse {

        // TODO : todo id 확인 없으면 throw
        // TODO : comment id 확인 없으면 throw
        // TODO : todo에 comment가 없으면 throw
        // TODO : comment의 writer와 password가 하나라도 다르면 throw
        // TODO : 내용 수정

        val todo : Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment : Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", id)

        todo.comments.find ({ it : Comment -> it == comment }) ?: throw IllegalStateException("todo doesn't have comment")


        if (request.writer != comment.writer) {
            throw IllegalStateException("Wrong writer")
        } else if (request.password != comment.password) {
            throw IllegalStateException("Wrong password")
        }

        comment.comment = request.comment
        commentRepository.save(comment)
        return comment.toResponse()
    }

    @Transactional
    override fun deleteComment(id: Long, commentId : Long, request : CommentDeleteRequest) : Unit{
        // TODO : todo id 확인 없으면 throw
        // TODO : comment id 확인 없으면 throw
        // TODO : todo에 comment가 없으면 throw
        // TODO : comment의 writer와 password가 하나라도 다르면 throw
        // TODO : 내용 삭제

        val todo : Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment : Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", id)

        todo.comments.find { it == comment } ?: throw IllegalStateException("todo doesn't have comment")


        if (request.writer != comment.writer || request.password != comment.password)
            throw IllegalStateException("Wrong writer or password")

        todo.comments.remove(comment)
        todoRepository.save(todo)

    }
}
