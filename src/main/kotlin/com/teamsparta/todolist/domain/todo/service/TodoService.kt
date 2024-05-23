package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest

interface TodoService {
    fun createTodo(request : TodoCreateRequest) : TodoResponse
    fun getTodos(orderByTime : Boolean, writer : String) : List<TodoResponse>
    fun getTodoById(id: Long) : TodoResponse
    fun updateTodoById(id : Long, request : TodoUpdateRequest, achievement: Boolean) : TodoResponse
    fun deleteTodoById(id : Long)

}