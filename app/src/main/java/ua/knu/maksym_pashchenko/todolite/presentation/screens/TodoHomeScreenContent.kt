package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoStats
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TaskFilter

@Composable
fun TodoHomeScreenContent(
    taskText: String,
    isError: Boolean,
    errorMessage: String?,
    allTasks: List<TodoItem>,
    visibleTasks: List<TodoItem>,
    selectedFilter: TaskFilter,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskCheckedChange: (TodoItem, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    onTaskRestore: (TodoItem) -> Unit,
    onTaskEdit: (TodoItem, String) -> Unit,
    onFilterSelected: (TaskFilter) -> Unit,
    onDeleteCompletedTasks: () -> Unit,
    modifier: Modifier = Modifier
) {
    var editingTask by remember { mutableStateOf<TodoItem?>(null) }
    var editText by rememberSaveable { mutableStateOf("") }

    var taskToDelete by remember { mutableStateOf<TodoItem?>(null) }
    var showClearCompletedDialog by rememberSaveable { mutableStateOf(false) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


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

            TodoFilterBar(
                selectedFilter = selectedFilter,
                onFilterSelected = onFilterSelected,
            )

            TodoDeleteCompletedTasksSection(
                enabled = allTasks.any { it.isDone },
                onDeleteCompletedTasks = {
                    showClearCompletedDialog = true
                }
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

            TodoList(
                tasks = visibleTasks,
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
        AlertDialog(
            onDismissRequest = {
                taskToDelete = null
            },
            title = {
                Text("Видалити задачу?")
            },
            text = {
                Text("Цю дію не можна скасувати.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
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
                    }
                ) {
                    Text("Видалити")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        taskToDelete = null
                    }
                ) {
                    Text("Скасувати")
                }
            }

        )
    }

    if (showClearCompletedDialog) {
        AlertDialog(
            onDismissRequest = {
                showClearCompletedDialog = false
            },
            title = {
                Text("Очистити всі виконані задачі?")
            },
            text = {
                Text("Цю дію не можна скасувати.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteCompletedTasks()
                        showClearCompletedDialog = false
                    }
                ) {
                    Text("Очистити")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showClearCompletedDialog = false
                    }
                ) {
                    Text("Скасувати")
                }
            }
        )
    }

    if (editingTask != null) {
        AlertDialog(
            onDismissRequest = {
                editingTask = null
                editText = ""
            },
            title = {
                Text(text = "Редагувати задачу")
            },
            text = {
                OutlinedTextField(
                    value = editText,
                    onValueChange = { editText = it },
                    singleLine = true,
                    label = { Text(text = "Новий текст") }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val trimmedText = editText.trim()

                        if (trimmedText.isNotEmpty()){
                            editingTask?.let { task ->
                                onTaskEdit(task, trimmedText)
                            }
                            editingTask = null
                            editText = ""
                        }
                    },
                    enabled = editText.trim().isNotEmpty()
                ) {
                    Text(text = "Зберегти")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        editingTask = null
                        editText = ""
                    }
                ) {
                    Text(text = "Скасувати")
                }
            }
        )
    }

}