package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoItemRow(
    task: TodoItem,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
    onTaskDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
                color = if (task.isDone) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface,
                textDecoration = if (task.isDone) TextDecoration.LineThrough else null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    onTaskDeleteClick(task.id)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Видалити задачу"
                )
            }
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { isChecked ->
                    onTaskCheckedChange(task.id, isChecked)
                },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TodoItemRowPreview() {
    TodoLiteTheme {
        TodoItemRow(
            task = TodoItem(1, "Купити телевізор", false),
            onTaskCheckedChange = {_, _ -> },
            onTaskDeleteClick = {}
        )
    }
}