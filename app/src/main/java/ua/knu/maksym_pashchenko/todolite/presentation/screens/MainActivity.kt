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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var tasks by rememberSaveable {
        mutableStateOf(listOf("Buy milk", "Study Kotlin", "Read Android docs"))
    }

    val onAddTaskClick = {
        val trimmedTaskText = taskText.trim()
        if (trimmedTaskText.isNotEmpty()){
            tasks = tasks + trimmedTaskText
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
        TodoList(tasks = tasks)
    }
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreen()
    }
}