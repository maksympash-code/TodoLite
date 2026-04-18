package ua.knu.maksym_pashchenko.todolite.presentation.viewmodels

import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem

data class TodoUiState(
    val taskText: String = "",
    val tasks: List<TodoItem> = emptyList(),
    val errorMessage: String? = null,
)
