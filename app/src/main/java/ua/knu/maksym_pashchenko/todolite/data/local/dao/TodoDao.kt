package ua.knu.maksym_pashchenko.todolite.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ua.knu.maksym_pashchenko.todolite.data.local.entity.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * from todo ORDER BY isDone ASC, id DESC")
    fun getAllTasks(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TodoEntity)

    @Update
    suspend fun updateTask(task: TodoEntity)

    @Query("DELETE FROM todo WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("DELETE FROM todo WHERE isDone = 1")
    suspend fun deleteCompletedTasks()
}