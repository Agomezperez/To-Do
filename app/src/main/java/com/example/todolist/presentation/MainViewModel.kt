package com.example.todolist.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.domain.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepo: TodoRepository
): ViewModel() {

    var todo: Todo by mutableStateOf(Todo (0, "", false))
        private set

    val getAllTodos = todoRepo.getAllTodos()

    private var deletedTodo: Todo? = null

    fun insert(todo: Todo) {
        viewModelScope.launch {
            todoRepo.insert(todo)
        }
    }

    fun update(todo: Todo) {
        viewModelScope.launch {
            todoRepo.update(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            deletedTodo = todo
            todoRepo.delete(todo)
        }
    }

    fun undoDelete() {
        deletedTodo?.let { todo ->
            viewModelScope.launch {
                todoRepo.insert(todo)
            }
        }
    }

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            todo = todoRepo.getTodoById(id)
        }
    }

    fun updateTask(task: String) {
        todo = todo.copy(task = task)
    }

    fun updateIsDone(isFavorite: Boolean) {
        todo = todo.copy(favorite = isFavorite)
    }

}