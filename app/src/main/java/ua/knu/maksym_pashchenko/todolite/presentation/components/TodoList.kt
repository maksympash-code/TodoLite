package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoList(
    tasks: List<TodoItem>,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (tasks.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
        ) {
            Text(text = "Задач поки немає")
            Text(text = "Додай першу задачу вище")
        }

    }
    else {
        LazyColumn(
            modifier = modifier
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = tasks,
                key = { task -> task.id }
            ) { task ->
                TodoItemRow(
                    task = task,
                    onTaskCheckedChange = onTaskCheckedChange,
                    onTaskDeleteClick = onTaskDeleteClick
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    TodoLiteTheme {
        TodoList(
            tasks = listOf(
                TodoItem(1, "Купити молоко", false),
                TodoItem(2, "Вчити Kotlin", true),
            ),
            onTaskCheckedChange = {_, _ ->},
            onTaskDeleteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoEmptyListPreview() {
    TodoLiteTheme {
        TodoList(
            tasks = emptyList(),
            onTaskCheckedChange = {_, _ ->},
            onTaskDeleteClick = {}
        )
    }
}