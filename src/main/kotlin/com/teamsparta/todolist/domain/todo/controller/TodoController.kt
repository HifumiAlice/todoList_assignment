package com.teamsparta.todolist.domain.todo.controller

import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse
import com.teamsparta.todolist.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController(private val todoService: TodoService) {

    @PostMapping()
    fun createTodo(
        @RequestBody request: TodoCreateRequest,
        @RequestParam(name = "member_id") memberId: Long
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(request, memberId))
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
    fun getTodo(@PathVariable id: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable("id") id: Long,
        @RequestParam(name = "achievement") achievement: Boolean = false,
        @RequestParam(name = "member_id") memberId: Long,
        @RequestBody request: TodoUpdateRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoById(id, request, achievement, memberId))
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(
        @PathVariable("id") id: Long,
        @RequestParam(name = "member_id") memberId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.deleteTodoById(id, memberId))
    }

}