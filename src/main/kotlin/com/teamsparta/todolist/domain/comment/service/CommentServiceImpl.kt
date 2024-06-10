package com.teamsparta.todolist.domain.comment.service

import com.teamsparta.todolist.domain.comment.dto.request.CommentCreateRequest
import com.teamsparta.todolist.domain.comment.dto.request.CommentUpdateRequest
import com.teamsparta.todolist.domain.comment.dto.response.CommentResponse
import com.teamsparta.todolist.domain.comment.model.Comment
import com.teamsparta.todolist.domain.comment.model.toResponse
import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.exception.UnAuthorizedException
import com.teamsparta.todolist.domain.member.adapter.MemberDetails
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository
) : CommentService {

    @PreAuthorize("hasRole('USER')")
    @Transactional
    override fun createComment(
        id: Long,
        request: CommentCreateRequest,
        memberDetails: MemberDetails?
    ): CommentResponse {

        memberDetails ?: throw UnAuthorizedException()

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val member: Member =
            memberRepository.findByIdOrNull(memberDetails.id) ?: throw ModelNotFoundException(
                "member",
                memberDetails.id
            )

        val comment: Comment = Comment(
            comment = request.comment,
            memberId = memberDetails.id
        )

        todo.addComment(comment)
        commentRepository.save(comment)

        return comment.toResponse(member)
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    override fun updateComment(
        id: Long,
        commentId: Long,
        request: CommentUpdateRequest,
        memberDetails: MemberDetails?
    ): CommentResponse {

        memberDetails ?: throw UnAuthorizedException()

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment: Comment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        val member: Member =
            memberRepository.findByIdOrNull(memberDetails.id) ?: throw ModelNotFoundException(
                "member",
                memberDetails.id
            )

        val isCommentBelongTodo: (it: Comment) -> Boolean = { it -> it == comment }

        todo.comments.find(isCommentBelongTodo) ?: throw IllegalStateException("Todo doesn't have comment")

        if (memberDetails.id != comment.memberId)
            throw UnAuthorizedException()

        comment.comment = request.comment
        commentRepository.save(comment)

        return comment.toResponse(member)
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    override fun deleteComment(id: Long, commentId: Long, memberDetails: MemberDetails?): Unit {

        memberDetails ?: throw UnAuthorizedException()

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val comment: Comment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)

        todo.comments.find { it == comment } ?: throw IllegalStateException("Todo doesn't have comment")


        if (memberDetails.id != comment.memberId)
            throw UnAuthorizedException()

        todo.removeComment(comment)
        todoRepository.save(todo)

    }
}
