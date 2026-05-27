package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoDeleteCompletedTasksSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoFilterBar
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoInputSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoList
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoSearchSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoStats
import ua.knu.maksym_pashchenko.todolite.presentation.components.dialogs.TodoClearCompletedDialog
import ua.knu.maksym_pashchenko.todolite.presentation.components.dialogs.TodoDeleteDialog
import ua.knu.maksym_pashchenko.todolite.presentation.components.dialogs.TodoEditDialog
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TaskFilter

@Composable
fun TodoHomeScreenContent(
    taskText: String,
    isError: Boolean,
    errorMessage: String?,
    allTasks: List<TodoItem>,
    visibleTasks: List<TodoItem>,
    selectedFilter: TaskFilter,
    searchText: String,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskCheckedChange: (TodoItem, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    onTaskRestore: (TodoItem) -> Unit,
    onTaskEdit: (TodoItem, String) -> Unit,
    onFilterSelected: (TaskFilter) -> Unit,
    onDeleteCompletedTasks: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var editingTask by remember { mutableStateOf<TodoItem?>(null) }
    var editText by rememberSaveable { mutableStateOf("") }

    var taskToDelete by remember { mutableStateOf<TodoItem?>(null) }
    var showClearCompletedDialog by rememberSaveable { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val emptyTitle = when {
        allTasks.isEmpty() -> "Задач поки немає"
        searchText.isNotBlank() -> "Нічого не знайдено"
        selectedFilter == TaskFilter.ACTIVE -> "Активних задач немає"
        selectedFilter == TaskFilter.COMPLETED -> "Виконаних задач немає"
        else -> "Задач поки немає"
    }

    val emptySubtitle = when {
        allTasks.isEmpty() -> "Додай першу задачу вище"
        searchText.isNotBlank() -> "Спробуй змінити текст пошуку"
        selectedFilter == TaskFilter.ACTIVE -> "Можеш відпочити або додати нову задачу"
        selectedFilter == TaskFilter.COMPLETED -> "Познач якусь задачу виконаною"
        else -> "Додай першу задачу вище"
    }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                totalTasks = allTasks.size,
                completedTasks = allTasks.count { it.isDone }
            )

            TodoSearchSection(
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                modifier = Modifier.padding(top = 8.dp),
            )

            TodoFilterBar(
                selectedFilter = selectedFilter,
                onFilterSelected = onFilterSelected,
                modifier = Modifier.padding(top = 8.dp)
            )

            TodoDeleteCompletedTasksSection(
                enabled = allTasks.any { it.isDone },
                onDeleteCompletedTasks = {
                    showClearCompletedDialog = true
                },
                modifier = Modifier.padding(top = 8.dp),
            )



            TodoList(
                tasks = visibleTasks,
                emptyTitle = emptyTitle,
                emptySubtitle = emptySubtitle,
                onTaskCheckedChange = onTaskCheckedChange,
                onTaskDeleteClick = { taskId ->
                    val selectedTask = visibleTasks.firstOrNull { it.id == taskId }
                    taskToDelete = selectedTask
                },
                onTaskEditClick = { task ->
                    editingTask = task
                    editText = task.title
                }
            )
        }
    }

    if (taskToDelete != null){
        TodoDeleteDialog(
            onConfirm = {
                val deletedTask = taskToDelete

                if (deletedTask != null) {
                    onTaskDeleteClick(deletedTask.id)
                    taskToDelete = null

                    scope.launch {
                        val result = snackBarHostState.showSnackbar(
                            message = "Задачу видалено",
                            actionLabel = "Скасувати",
                            duration = SnackbarDuration.Long
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            onTaskRestore(deletedTask)
                        }
                    }

                }
            },
            onDismiss = {
                taskToDelete = null
            }
        )
    }

    if (showClearCompletedDialog) {
        TodoClearCompletedDialog(
            onConfirm = {
                val deletedCompletedTasks = allTasks.filter { it.isDone }

                onDeleteCompletedTasks()
                showClearCompletedDialog = false

                scope.launch {
                    val result = snackBarHostState.showSnackbar(
                        message = "Виконані задачі очищено",
                        actionLabel = "Скасувати",
                        duration = SnackbarDuration.Long
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        deletedCompletedTasks.forEach { task ->
                            onTaskRestore(task)
                        }
                    }
                }
            },
            onDismiss = {
                showClearCompletedDialog = false
            }
        )
    }

    if (editingTask != null) {
        TodoEditDialog(
            editText = editText,
            onDismiss = {
                editingTask = null
                editText = ""
            },
            onConfirm = {
                val trimmedText = editText.trim()

                if (trimmedText.isNotEmpty()){
                    editingTask?.let { task ->
                        onTaskEdit(task, trimmedText)
                    }
                    editingTask = null
                    editText = ""
                }
            },
            onValueChange = { editText = it }
        )
    }
}