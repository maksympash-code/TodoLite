package ua.knu.maksym_pashchenko.todolite.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.knu.maksym_pashchenko.todolite.data.local.database.DatabaseProvider
import ua.knu.maksym_pashchenko.todolite.data.local.database.TodoDatabase
import ua.knu.maksym_pashchenko.todolite.data.repository.TodoRepositoryImpl
import ua.knu.maksym_pashchenko.todolite.presentation.screens.TodoHomeScreen
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoViewModel
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = TodoRepositoryImpl(database.todoDao())

        setContent {
            TodoLiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: TodoViewModel = viewModel(
                        factory = TodoViewModelFactory(repository)
                    )
                    TodoHomeScreen(
                        todoViewModel = viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}