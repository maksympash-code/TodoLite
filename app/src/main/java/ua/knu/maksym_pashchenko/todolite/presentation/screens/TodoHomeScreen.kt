package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoViewModel

@Composable
fun TodoHomeScreen(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel,
) {
    val tasks by todoViewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())

    TodoHomeScreenContent(
        taskText = todoViewModel.taskText,
        isError = todoViewModel.errorMessage != null,
        errorMessage = todoViewModel.errorMessage,
        tasks = tasks,
        onTaskTextChange = todoViewModel::onTaskTextChange,
        onAddTaskClick = todoViewModel::onAddTaskClick,
        onTaskCheckedChange = { task, isChecked ->
            todoViewModel.onTaskCheckedChange(task, isChecked)
        },
        onTaskDeleteClick = todoViewModel::onTaskDeleteClick,
        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreenContent(
            taskText = "Купити молоко",
            isError = false,
            errorMessage = "",
            tasks = listOf(
                TodoItem(1, "Купити молоко", false),
                TodoItem(2, "Вчити Kotlin", true)
            ),
            onTaskTextChange = {},
            onAddTaskClick = {},
            onTaskCheckedChange = { _, _ -> },
            onTaskDeleteClick = {}
        )
    }
}