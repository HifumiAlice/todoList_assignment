package com.teamsparta.todolist.domain.todo.controller

import com.teamsparta.todolist.domain.member.adapter.MemberDetails
import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse
import com.teamsparta.todolist.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController(private val todoService: TodoService) {

    @PostMapping()
    fun createTodo(
        @AuthenticationPrincipal member: MemberDetails?,
        @RequestBody request: TodoCreateRequest,
    ): ResponseEntity<TodoResponse> {

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(request, member))
    }

    @GetMapping()
    fun getTodos(
        @RequestParam(required = false, value = "orderByTime") orderByTime: Boolean = true,
        @RequestParam(required = false, value = "findMemberId") memberId: Long?
    ): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodos(orderByTime, memberId))
    }

    @GetMapping("/{id}")
    fun getTodo(
        @AuthenticationPrincipal member: MemberDetails?,
        @PathVariable id: Long
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @AuthenticationPrincipal member: MemberDetails?,
        @PathVariable("id") id: Long,
        @RequestParam(name = "achievement") achievement: Boolean = false,
        @RequestBody request: TodoUpdateRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoById(id, request, achievement, member))
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(
        @AuthenticationPrincipal member: MemberDetails?,
        @PathVariable("id") id: Long,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.deleteTodoById(id, member))
    }

}