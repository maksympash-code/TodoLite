package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
        OutlinedTextField(
            value = taskText,
            onValueChange = onTaskTextChange,
            label = { Text(text = "Нова задача") },
            placeholder = { Text(text = "Введіть текст задачі") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            isError = isError,
            supportingText = {
                if (errorMessage != null) {
                    Text(errorMessage)
                }
            }
        )
        Button(
            onClick = onAddTaskClick,
            modifier = Modifier
                .widthIn(min = 96.dp)
                .height(56.dp)
        ) {
            Text(
                text = "Додати",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_4",
    fontScale = 1.0f,
    locale = "uk"
)
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


@Preview(
    showBackground = true,
    device = "spec:width=360dp,height=640dp,dpi=480",
    fontScale = 1.3f,
    locale = "uk"
)
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