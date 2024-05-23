package com.teamsparta.todolist.domain.todo.controller

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.service.TodoService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController (private val todoService : TodoService) {

    @PostMapping()
    fun createTodo(@RequestBody request : TodoCreateRequest) : ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.createTodo(request))
    }

    @GetMapping()
    fun getTodos(@RequestParam(value = "orderByTime") orderByTime : Boolean = true,
                 @RequestParam(value = "findWriter") writer : String = "" ) : ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodos(orderByTime,writer))
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id : Long) : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable("id") id : Long, @RequestParam(name = "achievement") achievement : Boolean = false,
                   @RequestBody request : TodoUpdateRequest)
    : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoById(id, request, achievement ))
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable("id") id : Long) : ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.deleteTodoById(id))
    }

}