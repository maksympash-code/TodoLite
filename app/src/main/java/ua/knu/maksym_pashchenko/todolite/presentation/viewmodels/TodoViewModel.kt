package ua.knu.maksym_pashchenko.todolite.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.TodoConstants
import kotlin.collections.filter

class TodoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun onTaskTextChange(newValue: String) {
        _uiState.value = _uiState.value.copy(
            taskText = newValue,
            errorMessage = null
        )
    }

    fun onAddTaskClick() {
        val title = _uiState.value.taskText.trim()

        if (title.isEmpty()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = TodoConstants.EMPTY_TASK_ERROR
            )
            return
        }

        val newId = (_uiState.value.tasks.maxOfOrNull { it.id } ?: 0) + 1

        val newTask = TodoItem(
            title = title,
            id =  newId,
            isDone = false
        )

        _uiState.value = _uiState.value.copy(
            tasks = _uiState.value.tasks + newTask,
            taskText = "",
            errorMessage = null
        )
    }

    fun onTaskCheckedChange(taskId: Int, isChecked: Boolean) {
        val updatedTasks = _uiState.value.tasks.map { task ->
            if (task.id == taskId) task.copy(isDone = isChecked)
            else task
        }

        _uiState.value = _uiState.value.copy(tasks = updatedTasks)
    }

    fun onTaskDeleteClick(taskId: Int) {
        val updatedTasks = _uiState.value.tasks.filter { it.id != taskId }
        _uiState.value = _uiState.value.copy(tasks = updatedTasks)
    }
}