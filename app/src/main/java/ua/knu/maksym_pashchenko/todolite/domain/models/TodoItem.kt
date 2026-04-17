package ua.knu.maksym_pashchenko.todolite.domain.models

data class TodoItem(
    val id: Int,
    val title: String,
    val isDone: Boolean
)