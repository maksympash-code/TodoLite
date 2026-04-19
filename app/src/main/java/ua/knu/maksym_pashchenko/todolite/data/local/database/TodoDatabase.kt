package ua.knu.maksym_pashchenko.todolite.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.knu.maksym_pashchenko.todolite.data.local.dao.TodoDao
import ua.knu.maksym_pashchenko.todolite.data.local.entity.TodoEntity


@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}