package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
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
    private val commentRepository: CommentRepository
) : TodoService {

    @Transactional
    override fun createTodo(request: TodoCreateRequest): TodoResponse {

        checkRequest(request.title, request.content, request.writer)

        val todo: Todo = Todo(
            title = request.title,
            content = request.content,
            date = request.date,
            writer = request.writer
        )

        todoRepository.save(todo)

        return todo.toResponse()
    }

    override fun getTodos(orderByTime: Boolean, writer: String): List<TodoResponse> {

        return if (writer.isNotEmpty()) {
            when (orderByTime) {
                true -> todoRepository.findAllByWriterOrderByDateDesc(writer).map { it.toResponse() }
                false -> todoRepository.findAllByWriterOrderByDateAsc(writer).map { it.toResponse() }
            }
        } else {
            when (orderByTime) {
                true -> todoRepository.findAllByOrderByDateDesc().map { it.toResponse() }
                false -> todoRepository.findAllByOrderByDateAsc().map { it.toResponse() }
            }
        }

    }

    override fun getTodoById(id: Long): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        return todo.toResponse(false)

    }

    @Transactional
    override fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement: Boolean): TodoResponse {

        val todo: Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)

        if (todo.achievement == true) throw IllegalStateException("Already achievement is true")

        if (achievement == true) todo.achievement = true
        else {
            if (request.writer != todo.writer) throw IllegalStateException("Writer isn't same")
            checkRequest(request.title, request.content, "OK")


            todo.title = request.title
            todo.content = request.content
            todo.writer = request.writer
        }

        todoRepository.save(todo)


        return todo.toResponse()
    }

    @Transactional
    override fun deleteTodoById(id: Long) {

        todoRepository.delete(todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id))

    }

    fun checkRequest(title: String, content: String, writer: String) {
        if (title.isEmpty() || title.length > 200) throw IllegalArgumentException("Invalid title that is empty or long")
        if (content.isEmpty() || content.length > 1000) throw IllegalArgumentException("Invalid content that is empty or long")
        if (writer.isEmpty()) throw IllegalArgumentException("Invalid writer that is empty")
    }

}

