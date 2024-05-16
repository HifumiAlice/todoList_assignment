package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.model.toResponse
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class TodoServiceImplement(private val todoRepository : TodoRepository) : TodoService {

    @Transactional
    override fun createTodo(request: TodoCreateRequest): TodoResponse {
        // TODO : 구성요소가 잘 맞는지 확인하기 --> 요청이 잘못들어오면 애초에 거절됨
        // TODO : db에 Todo 저장하고 데이터 반환

        val todo : Todo = Todo(title = request.title,
            content = request.content,
            date = request.date,
            writer = request.writer)
        todoRepository.save(todo)
        return todo.toResponse()
    }

    override fun getTodos(): List<TodoResponse> {
        // TODO : DB에서 Todo 목록 가져와서 보여주기
        TODO("Not yet implemented")
    }

    override fun getTodoById(id: Long): TodoResponse {
        // TODO : DB에서 원하는 Todo 가져오기
        // TODO : 만약 Id가 없으면 예외처리
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodoById(id: Long, request: TodoUpdateRequest): TodoResponse {
        // TODO : DB에서 Todo 가져와서 데이터 수정하고 다시 저장하기
        // TODO : 만약 Id가 없으면 예외처리

        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodoById(id: Long) {
        // TODO : DB에서 Todo 가져와서 삭제하기
        // TODO : 만약 Id가 없다면 예외처리
        TODO("Not yet implemented")
    }

}