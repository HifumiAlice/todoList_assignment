package com.teamsparta.todolist.domain.todo.controller

import com.teamsparta.todolist.domain.todo.dto.request.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.request.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.dto.response.TodoResponse
import com.teamsparta.todolist.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RequestMapping("/todos")
@RestController
class TodoController(private val todoService: TodoService) {

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    fun createTodo(@RequestBody request: TodoCreateRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(request))
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    fun getTodos(
        @RequestParam(required = false, value = "orderByTime") orderByTime: Boolean = true,
        @RequestParam(required = false, value = "findMemberId") memberId: Long?
    ): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodos(orderByTime, memberId))
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable("id") id: Long, @RequestParam(name = "achievement") achievement: Boolean = false,
        @RequestBody request: TodoUpdateRequest
    )
            : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoById(id, request, achievement))
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.deleteTodoById(id))
    }

}