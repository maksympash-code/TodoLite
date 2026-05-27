package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TaskFilter
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TodoViewModel

@Composable
fun TodoHomeScreen(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel,
) {
    val tasks by todoViewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList())

    val filteredTasks = todoViewModel.filterTasks(tasks)

    TodoHomeScreenContent(
        taskText = todoViewModel.taskText,
        isError = todoViewModel.errorMessage != null,
        errorMessage = todoViewModel.errorMessage,
        allTasks = tasks,
        visibleTasks = filteredTasks,
        selectedFilter = todoViewModel.selectedFilter,
        searchText = todoViewModel.searchText,
        onTaskTextChange = todoViewModel::onTaskTextChange,
        onAddTaskClick = todoViewModel::onAddTaskClick,
        onTaskCheckedChange = { task, isChecked ->
            todoViewModel.onTaskCheckedChange(task, isChecked)
        },
        onTaskDeleteClick = todoViewModel::onTaskDeleteClick,
        onTaskRestore = todoViewModel::onTaskRestore,
        onTaskEdit = todoViewModel::onTaskEdit,
        onFilterSelected = todoViewModel::onFilterSelected,
        onDeleteCompletedTasks = todoViewModel::onDeleteCompletedTasks,
        onSearchTextChange = todoViewModel::onSearchTextChange,
        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreviewMixedTasks() {
    TodoLiteTheme {
        TodoHomeScreenContent(
            taskText = "Купити молоко",
            isError = false,
            errorMessage = "",
            allTasks = listOf(),
            visibleTasks = listOf(
                TodoItem(1, "Купити молоко", false),
                TodoItem(2, "Вчити Kotlin", true)
            ),
            selectedFilter = TaskFilter.ALL,
            searchText = "",
            onTaskTextChange = {},
            onAddTaskClick = {},
            onTaskCheckedChange = { _, _ -> },
            onTaskDeleteClick = {},
            onTaskRestore = {_ -> },
            onTaskEdit = {_, _ -> },
            onFilterSelected = {},
            onDeleteCompletedTasks = {},
            onSearchTextChange = {_ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreviewEmptyState() {
    TodoLiteTheme {
        TodoHomeScreenContent(
            taskText = "Купити молоко",
            isError = false,
            errorMessage = "",
            allTasks = listOf(),
            visibleTasks = listOf(),
            selectedFilter = TaskFilter.ALL,
            searchText = "Купити молоко",
            onTaskTextChange = {},
            onAddTaskClick = {},
            onTaskCheckedChange = { _, _ -> },
            onTaskDeleteClick = {},
            onTaskRestore = {_ -> },
            onTaskEdit = {_, _ -> },
            onFilterSelected = {},
            onDeleteCompletedTasks = {},
            onSearchTextChange = {_ -> }
        )
    }
}