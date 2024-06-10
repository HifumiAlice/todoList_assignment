package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.member.adapter.MemberDetails
import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse

interface TodoService {
    fun createTodo(request: TodoCreateRequest,memberDetails: MemberDetails?): TodoResponse
    fun getTodos(orderByTime: Boolean, memberId: Long?): List<TodoResponse>
    fun getTodoById(id: Long): TodoResponse
    fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement: Boolean, memberDetails: MemberDetails?): TodoResponse
    fun deleteTodoById(id: Long, memberDetails: MemberDetails?)

}