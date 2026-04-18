package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoInputSection(
    taskText: String,
    isError: Boolean,
    errorMessage: String?,
    onTaskTextChange: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = taskText,
            onValueChange = onTaskTextChange,
            label = { Text("Нова задача") },
            placeholder = { Text("Введіть текст задачі") },
            isError = isError,
            supportingText = {
                if (errorMessage != null)
                    Text(text = errorMessage)
            },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = onAddTaskClick,
            modifier = Modifier
                .width(120.dp)
        ) {
            Text(
                text = "Додати",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TodoInputSectionPreview() {
    TodoLiteTheme {
        TodoInputSection(
            taskText = "",
            onTaskTextChange = {},
            onAddTaskClick = {},
            isError = false,
            errorMessage = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodoInputSectionErrorPreview() {
    TodoLiteTheme {
        TodoInputSection(
            taskText = "",
            onTaskTextChange = {},
            onAddTaskClick = {},
            isError = true,
            errorMessage = "Назва задачі не може бути порожньою"
        )
    }
}