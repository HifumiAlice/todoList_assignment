package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse

interface TodoService {
    fun createTodo(request: TodoCreateRequest): TodoResponse
    fun getTodos(orderByTime: Boolean, writer: String): List<TodoResponse>
    fun getTodoById(id: Long): TodoResponse
    fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement: Boolean): TodoResponse
    fun deleteTodoById(id: Long)

}