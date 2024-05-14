package com.teamsparta.todolist.domain.todo.controller

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController {

    @PostMapping()
    fun createPosting(@RequestBody request : TodoCreateRequest) : ResponseEntity<TodoResponse> {
        TODO("Not yet implemented")
    }

    @GetMapping()
    fun getCardAll() : ResponseEntity<TodoResponse> {
        TODO("Not yet implemented")
    }

    @GetMapping("/{id}")
    fun getCardById(@PathVariable id : Long) : ResponseEntity<TodoResponse> {
        TODO("Not yet implemented")
    }

    @PutMapping("/{id}")
    fun updateCard(@PathVariable id : Long, @RequestBody request : TodoUpdateRequest) : ResponseEntity<TodoResponse> {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable id : Long) : ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}