package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoInputSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoList
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoStats

@Composable
fun TodoHomeScreenContent(
    taskText: String,
    isError: Boolean,
    errorMessage: String?,
    tasks: List<TodoItem>,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskCheckedChange: (TodoItem, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier.padding(bottom = 20.dp),
        )

        TodoInputSection(
            taskText = taskText,
            onTaskTextChange = onTaskTextChange,
            onAddTaskClick = onAddTaskClick,
            isError = isError,
            errorMessage = errorMessage
        )

        TodoStats(
            totalTasks = tasks.size,
            completedTasks = tasks.count { it.isDone }
        )

        TodoList(
            tasks = tasks,
            onTaskCheckedChange = onTaskCheckedChange,
            onTaskDeleteClick = onTaskDeleteClick,
        )
    }
}