package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoInputSection(modifier: Modifier = Modifier) {
    var taskText by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("Нова задача") },
            placeholder = { Text("Введіть текст задачі") },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {},
            modifier = Modifier
                .width(120.dp)
        ) {
            Text(
                text = "Add task",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TodoInputSectionPreview() {
    TodoLiteTheme {
        TodoInputSection()
    }
}