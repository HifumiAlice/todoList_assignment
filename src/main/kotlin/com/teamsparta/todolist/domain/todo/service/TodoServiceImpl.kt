package com.teamsparta.todolist.domain.todo.service

import com.teamsparta.todolist.domain.comment.repository.CommentRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.todo.dto.TodoCreateRequest
import com.teamsparta.todolist.domain.todo.dto.TodoResponse
import com.teamsparta.todolist.domain.todo.dto.TodoUpdateRequest
import com.teamsparta.todolist.domain.todo.model.Todo
import com.teamsparta.todolist.domain.todo.model.toResponse
import com.teamsparta.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TodoServiceImpl(
    private val todoRepository : TodoRepository,
    private val commentRepository : CommentRepository
) : TodoService {

    @Transactional
    override fun createTodo(request: TodoCreateRequest): TodoResponse {
        // TODO : 구성요소가 잘 맞는지 확인하기 --> 요청이 잘못들어오면 애초에 거절됨
        // TODO : db에 Todo 저장하고 데이터 반환

        checkRequest(request.title, request.content, request.writer)

        val todo : Todo = Todo(title = request.title,
            content = request.content,
            date = request.date,
            writer = request.writer)

        todoRepository.save(todo)

        return todo.toResponse()
    }

    override fun getTodos(orderByTime : Boolean, writer : String): List<TodoResponse> {
        // TODO : DB에서 Todo 목록 가져와서 보여주기
        // TODO : 정렬하기 --> 할 거 다 하고 구현하기
        // TODO : 시간순으로 오름차순 or 내림차순 구현 // 기본 값이 내림차순으로 해둘 거임
        // TODO : 작성자 검색 시 시간순 내림차순 정렬

        if(writer.isNotEmpty()) return todoRepository.findAllByWriterOrderByDateDesc(writer).map { it.toResponse()}

        return when (orderByTime){
            true -> todoRepository.findAllByOrderByDateDesc().map { it.toResponse() }
            false -> todoRepository.findAllByOrderByDateAsc().map { it.toResponse() }
        }


    }

    override fun getTodoById(id: Long): TodoResponse {
        // TODO : DB에서 원하는 Todo 가져오기
        // TODO : 만약 Id가 없으면 예외처리

        val todo : Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo",id)

        return todo.toResponse(false)

    }

    @Transactional
    override fun updateTodoById(id: Long, request: TodoUpdateRequest, achievement : Boolean): TodoResponse {
        // TODO : DB에서 Todo 가져와서 데이터 수정하고 다시 저장하기
        // TODO : 만약 Id가 없으면 예외처리

        // TODO : 추가사항 --> 완료 여부 수정 // Policy : 완료 체크하면 수정 불가 --> 완료 전 상태로 못 돌아감
        // TODO : 완료되면 내용 수정 불가

        // TODO : 작성자가 같은지 확인 --> 다르면 예외처리
        // TODO : 제목과 내용이 유효한지 확인 --> 유효하지 않으면 예외처리


        val todo : Todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo",id)

        if (todo.achievement == true) throw IllegalStateException("Already achievement is true")

        if(achievement == true) todo.achievement = true
        else {
            if(request.writer != todo.writer) throw IllegalStateException("Writer isn't same")
            checkRequest(request.title,request.content,"OK")


            todo.title = request.title
            todo.content = request.content
            todo.writer = request.writer
        }

        todoRepository.save(todo)


        return todo.toResponse()
    }

    @Transactional
    override fun deleteTodoById(id: Long) {
        // TODO : DB에서 Todo 가져와서 삭제하기
        // TODO : 만약 Id가 없다면 예외처리
        todoRepository.delete(todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo",id))

    }

    fun checkRequest(title : String, content : String, writer : String) {
        if (title.isEmpty() || title.length > 200) throw IllegalArgumentException("Invalid title that is empty or long")
        if (content.isEmpty() || content.length > 1000) throw IllegalArgumentException("Invalid content that is empty or long")
        if (writer.isEmpty()) throw IllegalArgumentException("Invalid writer that is empty")
    }

}

