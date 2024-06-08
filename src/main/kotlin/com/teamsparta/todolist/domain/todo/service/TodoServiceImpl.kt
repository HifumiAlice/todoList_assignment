package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.member.model.Member
import com.teamsparta.todolist.domain.member.repository.MemberRepository
import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.model.toResponse
import com.teamsparta.todolist.domain.todo.model.toResponseWithComments
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository
) : TodoService {

    @Transactional
    override fun createTodo(request: TodoCreateRequest, memberId: Long): TodoResponse {

        val member: Member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException(
            "member",
            memberId
        )

        checkRequest(request.title, request.content)

        val todo: Todo = Todo(
            title = request.title,
            content = request.content,
            memberId = memberId
        )

        todoRepository.save(todo)

        return todo.toResponse(member = member)
    }
    @Transactional
    override fun getTodos(orderByTime: Boolean, memberId: Long?): List<TodoResponse> {

        return if (memberId != null) {
            val member: Member =
                memberRepository.findByIdOne(memberId)
            when (orderByTime) {
                true -> todoRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId)
                    .map { it.toResponse(member = member) }

                false -> todoRepository.findAllByMemberIdOrderByCreatedAtAsc(memberId)
                    .map { it.toResponse(member = member) }
            }
        } else {
            val members : List<Member> = memberRepository.findAll()
            when (orderByTime) {
                true -> todoRepository.findAllByOrderByCreatedAtDesc()
                    .map { it.toResponse(member = members.find{ item -> item.id == it.memberId}!!) }

                false -> todoRepository.findAllByOrderByCreatedAtAsc()
                    .map { it.toResponse(member = members.find{ item -> item.id == it.memberId}!!) }
            }
        }

    }

    override fun getTodoById(id: Long): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val members: List<Member> = memberRepository.findAll()
        return todo.toResponseWithComments(members.find{it.id == todo.memberId}!!, members)

    }

    @Transactional
    override fun updateTodoById(
        id: Long,
        request: TodoUpdateRequest,
        achievement: Boolean,
        memberId: Long
    ): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        if (memberId != todo.memberId) throw IllegalArgumentException("MemberId isn't same")

        if (todo.achievement) throw IllegalStateException("Already achievement is true")

        if (achievement) todo.achievement = true
        else {
            checkRequest(request.title, request.content)

            todo.title = request.title
            todo.content = request.content
        }

        todoRepository.save(todo)

        return todo.toResponse(memberRepository.findByIdOne(todo.memberId))
    }

    @Transactional
    override fun deleteTodoById(id: Long, memberId: Long) {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        if (todo.memberId != memberId) throw IllegalArgumentException("MemberId isn't same")
        todoRepository.delete(todo)

    }

    fun checkRequest(title: String, content: String) {
        if (title.isEmpty() || title.length > 200) throw IllegalArgumentException("Invalid title that is empty or long")
        if (content.isEmpty() || content.length > 1000) throw IllegalArgumentException("Invalid content that is empty or long")
    }

}

