package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoList(
    tasks: List<String>,
    modifier: Modifier = Modifier,
) {
    if (tasks.isEmpty()) {
        Text(
            text = "No tasks yet",
            modifier = Modifier.padding(top = 24.dp)
        )
    }
    else {
        LazyColumn(
            modifier = modifier
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TodoItemRow(
                    title = task,
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
            tasks = listOf("Buy milk", "Study Kotlin")
        )
    }
}