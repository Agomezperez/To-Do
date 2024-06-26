package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoDao
import com.example.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoDao: TodoDao
) {

    suspend fun insert(todo: Todo) = todoDao.insert(todo)

    suspend fun update(todo: Todo) = todoDao.update(todo)

    suspend fun delete(todo: Todo) = todoDao.delete(todo)

    suspend fun getTodoById(id: Int): Todo = todoDao.getTodoById(id)

    fun getAllTodos(): Flow<List<Todo>> = todoDao.getAllTodos()
}