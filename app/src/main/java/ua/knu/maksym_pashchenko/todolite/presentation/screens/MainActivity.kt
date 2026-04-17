package ua.knu.maksym_pashchenko.todolite.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoInputSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoList
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoLiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoHomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TodoHomeScreen(
    modifier: Modifier = Modifier
) {
    var taskText by rememberSaveable { mutableStateOf("") }
    var tasks by remember {
        mutableStateOf(listOf(
            TodoItem(1, "Buy milk", false),
            TodoItem(2, "Study Kotlin", false),
            TodoItem(3, "Read Android docs", true)
            )
        )
    }

    val onAddTaskClick = {
        val trimmedTaskText = taskText.trim()
        if (trimmedTaskText.isNotEmpty()){
            val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
            tasks = tasks + TodoItem(
                id = newId,
                title = trimmedTaskText,
                isDone = false
            )
            taskText = ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Todo Lite",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TodoInputSection(
            taskText = taskText,
            onTaskTextChange = { taskText = it },
            onAddTaskClick = onAddTaskClick,
            enabled = taskText.trim().isNotEmpty()
        )
        TodoList(
            tasks = tasks,
            onTaskCheckedChange = { taskId, isChecked ->
                tasks = tasks.map { task ->
                    if (taskId == task.id) task.copy(isDone = isChecked)
                    else task
                }
            },
            onTaskDeleteClick = { taskId ->
                tasks = tasks.filter { it.id != taskId }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreen()
    }
}