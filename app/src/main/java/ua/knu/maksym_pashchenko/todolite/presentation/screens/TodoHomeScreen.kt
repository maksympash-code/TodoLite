package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoUiState
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoViewModel

@Composable
fun TodoHomeScreen(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel = viewModel(),
) {
    val uiState by todoViewModel.uiState.collectAsStateWithLifecycle()

    TodoHomeScreenContent(
        uiState = uiState,
        onTaskTextChange = todoViewModel::onTaskTextChange,
        onAddTaskClick = todoViewModel::onAddTaskClick,
        onTaskCheckedChange = todoViewModel::onTaskCheckedChange,
        onTaskDeleteClick = todoViewModel::onTaskDeleteClick,

        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreenContent(
            uiState = TodoUiState(
                taskText = "Купити молоко",
                tasks = listOf(
                    TodoItem(1, "Купити молоко", false),
                    TodoItem(2, "Вчити Kotlin", true)
                )
            ),
            onTaskTextChange = {},
            onAddTaskClick = {},
            onTaskCheckedChange = { _, _ -> },
            onTaskDeleteClick = {}
        )
    }
}