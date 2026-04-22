package ua.knu.maksym_pashchenko.todolite.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    tasks: List<TodoItem>,
    selectedFilter: TaskFilter,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskCheckedChange: (TodoItem, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    onTaskEdit: (TodoItem, String) -> Unit,
    onFilterSelected: (TaskFilter) -> Unit,
    onDeleteCompletedTasks: () -> Unit,
    modifier: Modifier = Modifier
) {
    var editingTask by remember { mutableStateOf<TodoItem?>(null) }
    var editText by rememberSaveable { mutableStateOf("") }

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

        TodoFilterBar(
            selectedFilter = selectedFilter,
            onFilterSelected = onFilterSelected,
        )

        TodoDeleteCompletedTasksSection(
            onDeleteCompletedTasks = onDeleteCompletedTasks
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
            onTaskEditClick = { task ->
                editingTask = task
                editText = task.title
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
                            onTaskEdit(editingTask!!, trimmedText)
                            editingTask = null
                            editText = ""
                        }
                    }
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