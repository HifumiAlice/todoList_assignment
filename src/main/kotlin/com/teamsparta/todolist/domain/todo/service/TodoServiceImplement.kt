package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class TodoServiceImplement : TodoService {

    @Transactional
    override fun createTodo(request: TodoCreateRequest): TodoResponse {
        // TODO : 구성요소가 잘 맞는지 확인하기
        // TODO : db에 Todo 저장하고 데이터 반환
        TODO("Not yet implemented")
    }

    override fun getTodos(): List<TodoResponse> {
        // TODO : DB에서 Todo 목록 가져와서 보여주기
        TODO("Not yet implemented")
    }

    override fun getTodoById(id: Long): TodoResponse {
        // TODO : DB에서 원하는 Todo 가져오기
        // TODO : 만약 Id가 없으면 예외처리
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodoById(id: Long, request: TodoUpdateRequest): TodoResponse {
        // TODO : DB에서 Todo 가져와서 데이터 수정하고 다시 저장하기
        // TODO : 만약 Id가 없으면 예외처리

        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodoById(id: Long) {
        // TODO : DB에서 Todo 가져와서 삭제하기
        // TODO : 만약 Id가 없다면 예외처리
        TODO("Not yet implemented")
    }

}