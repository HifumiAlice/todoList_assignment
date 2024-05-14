package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest

interface TodoService {
    fun createTodo(request : TodoCreateRequest) : TodoResponse
    fun getTodos() : List<TodoResponse>
    fun getTodoById(id: Long) : TodoResponse
    fun updateTodoById(id : Long, request : TodoUpdateRequest) : TodoResponse
    fun deleteTodoById(id : Long)
}