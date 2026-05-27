package ua.knu.maksym_pashchenko.todolite.presentation.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun TodoEditDialog(
    editText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onValueChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Редагувати задачу")
        },
        text = {
            OutlinedTextField(
                value = editText,
                onValueChange = onValueChange,
                singleLine = true,
                label = { Text(text = "Новий текст") }
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = editText.trim().isNotEmpty()
            ) {
                Text("Зберегти")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
        }
    )
}