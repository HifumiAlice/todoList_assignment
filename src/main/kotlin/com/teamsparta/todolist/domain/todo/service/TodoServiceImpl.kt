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


    override fun createTodo(request: TodoCreateRequest): TodoResponse {
        val member: Member = memberRepository.findByIdOrNull(request.memberId) ?: throw ModelNotFoundException(
            "member",
            request.memberId
        )
        checkRequest(request.title, request.content)

        val todo: Todo = Todo(
            title = request.title,
            content = request.content,
            memberId = request.memberId,
        )

        todoRepository.save(todo)

        return todo.toResponse(member = member)
    }

    override fun getTodos(orderByTime: Boolean, memberId: Long?): List<TodoResponse> {

        // TODO : 현재 memberId를 안받으면 아이디 찾기가 안됨 --> jwt를 통해서 memberId를 받는 방법 찾으면 개선
        return if (memberId != null) {
            when (orderByTime) {
                true -> todoRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId)
                    .map { it.toResponse(member = memberRepository.findByIdOne(memberId)) }

                false -> todoRepository.findAllByMemberIdOrderByCreatedAtAsc(memberId)
                    .map { it.toResponse(member = memberRepository.findByIdOne(memberId)) }

            }
        } else {
            when (orderByTime) {
                true -> todoRepository.findAllByOrderByCreatedAtDesc().map { it.toResponse(member = memberRepository.findByIdOne(it.memberId)) }
                false -> todoRepository.findAllByOrderByCreatedAtAsc().map { it.toResponse(member = memberRepository.findByIdOne(it.memberId)) }
            }
        }
    }


    override fun getTodoById(id: Long): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        return todo.toResponse(isAll = false, member = memberRepository.findByIdOrNull(todo.memberId)!!)

    }

    @Transactional
    override fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement: Boolean): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        if (todo.achievement == true) throw IllegalStateException("Already achievement is true")

        if (achievement == true) todo.achievement = true
        else {
//            if (request.writer != todo.writer) throw IllegalStateException("Writer isn't same")
            checkRequest(request.title, request.content)


            todo.title = request.title
            todo.content = request.content
        }

        todoRepository.save(todo)


        return todo.toResponse(member = memberRepository.findByIdOne(todo.memberId))
    }

    @Transactional
    override fun deleteTodoById(id: Long) {

        todoRepository.delete(todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id))

    }

    fun checkRequest(title: String, content: String) {
        if (title.isEmpty() || title.length > 200) throw IllegalArgumentException("Invalid title that is empty or long")
        if (content.isEmpty() || content.length > 1000) throw IllegalArgumentException("Invalid content that is empty or long")

    }

}

