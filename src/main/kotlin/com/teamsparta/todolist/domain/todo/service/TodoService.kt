package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse

interface TodoService {
    fun createTodo(request: TodoCreateRequest, memberId: Long): TodoResponse
    fun getTodos(orderByTime: Boolean, memberId: Long?): List<TodoResponse>
    fun getTodoById(id: Long): TodoResponse
    fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement: Boolean, memberId: Long): TodoResponse
    fun deleteTodoById(id: Long, memberId: Long)

}