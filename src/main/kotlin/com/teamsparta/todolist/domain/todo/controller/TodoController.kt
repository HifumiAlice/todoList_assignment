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
    fun getTodos() : ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodos())
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id : Long) : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable("id") id : Long, @RequestBody request : TodoUpdateRequest) : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodoById(id, request ))
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable("id") id : Long) : ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.deleteTodoById(id))
    }

    @PutMapping("/{id}/achievements")
    fun todoAchieve(@PathVariable("id") id : Long, @RequestParam(value = "achievement") achievement : Boolean, @RequestParam(name = "test") test:String?) : ResponseEntity<TodoResponse> {
        /*
        TODO : 현재는 API가 분리되어 있음 --> 추후 updateTodo에 합칠 예정
        */

        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodoByIdByAchievement(id,achievement ))
    }
}