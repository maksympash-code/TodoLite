package ua.knu.maksym_pashchenko.todolite.data.local.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    fun getDatabase(context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }
}