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
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoInputSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoList
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoStats
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoUiState

@Composable
fun TodoHomeScreenContent(
    uiState: TodoUiState,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
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
            taskText = uiState.taskText,
            onTaskTextChange = onTaskTextChange,
            onAddTaskClick = onAddTaskClick,
            isError = uiState.errorMessage != null,
            errorMessage = uiState.errorMessage
        )
        TodoStats(
            totalTasks = uiState.tasks.size,
            completedTasks = uiState.tasks.count { it.isDone }
        )
        TodoList(
            tasks = uiState.tasks,
            onTaskCheckedChange = onTaskCheckedChange,
            onTaskDeleteClick = onTaskDeleteClick,
        )
    }
}