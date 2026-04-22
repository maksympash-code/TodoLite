package ua.knu.maksym_pashchenko.todolite.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.knu.maksym_pashchenko.todolite.data.local.dao.TodoDao
import ua.knu.maksym_pashchenko.todolite.data.local.mappers.toDomain
import ua.knu.maksym_pashchenko.todolite.data.local.mappers.toEntity
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.domain.repositories.TodoRepository

class TodoRepositoryImpl(
    private val todoDao: TodoDao
): TodoRepository {
    override fun getAllTasks(): Flow<List<TodoItem>> {
        return todoDao.getAllTasks().map { entityList ->
            entityList.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun addTask(task: TodoItem) {
        todoDao.addTask(task.toEntity())
    }

    override suspend fun updateTask(task: TodoItem) {
        todoDao.updateTask(task.toEntity())
    }

    override suspend fun deleteTaskById(taskId: Int) {
        todoDao.deleteTaskById(taskId)
    }

    override suspend fun deleteCompletedTasks() {
        todoDao.deleteCompletedTasks()
    }
}