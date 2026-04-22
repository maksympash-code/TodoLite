package ua.knu.maksym_pashchenko.todolite.domain.repositories

import kotlinx.coroutines.flow.Flow
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem

interface TodoRepository {
    fun getAllTasks(): Flow<List<TodoItem>>
    suspend fun addTask(task: TodoItem)
    suspend fun updateTask(task: TodoItem)
    suspend fun deleteTaskById(taskId: Int)
    suspend fun deleteCompletedTasks()
}