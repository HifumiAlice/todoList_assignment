package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentDeleteRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import com.teamsparta.todolist.domain.comment.model.Comment
import com.teamsparta.todolist.domain.comment.model.toResponse
import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository
) : CommentService {

    @Transactional
    override fun createComment(id: Long, request: CommentCreateRequest, memberId: Long): CommentResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val member: Member =
            memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("member", memberId)

        val comment: Comment = Comment(
            comment = request.comment,
            memberId = memberId
        )

        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse(member)
    }

    @Transactional
    override fun updateComment(
        id: Long,
        commentId: Long,
        request: CommentUpdateRequest,
        memberId: Long
    ): CommentResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment: Comment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        val member: Member =
            memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("member", memberId)

        val isCommentBelongTodo: (it: Comment) -> Boolean = { it -> it == comment }

        todo.comments.find(isCommentBelongTodo) ?: throw IllegalStateException("Todo doesn't have comment")

        if (memberId != comment.memberId) {
            throw IllegalStateException("Wrong memberId")
        }

        comment.comment = request.comment
        commentRepository.save(comment)

        return comment.toResponse(member)
    }

    @Transactional
    override fun deleteComment(id: Long, commentId: Long, memberId: Long): Unit {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment: Comment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)

        todo.comments.find { it == comment } ?: throw IllegalStateException("Todo doesn't have comment")


        if (memberId != comment.memberId )
            throw IllegalStateException("Wrong memberId")

        todo.removeComment(comment)
        todoRepository.save(todo)

    }
}
