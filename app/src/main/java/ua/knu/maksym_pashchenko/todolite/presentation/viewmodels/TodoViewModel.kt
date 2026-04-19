package ua.knu.maksym_pashchenko.todolite.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.domain.repositories.TodoRepository

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val tasks: Flow<List<TodoItem>> = repository.getAllTasks()

    var taskText by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onTaskTextChange(newText: String) {
        taskText = newText

        if (newText.isNotBlank()) {
            errorMessage = null
        }
    }

    fun onAddTaskClick() {
        val cleanTitle = taskText.trim()

        if (cleanTitle.isEmpty()) {
            errorMessage = "Поле не може бути порожнім"
            return
        }

        viewModelScope.launch {
            repository.addTask(
                TodoItem(
                    id = 0,
                    title = cleanTitle,
                    isDone = false
                )
            )
        }

        taskText = ""
        errorMessage = null
    }

    fun onTaskCheckedChange(task: TodoItem, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isDone = isChecked))
        }
    }

    fun onTaskDeleteClick(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTaskById(taskId)
        }
    }
}