package ua.knu.maksym_pashchenko.todolite.presentation.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun TodoClearCompletedDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Очистити всі виконані задачі?")
        },
        text = {
            Text("Виконані задачі будуть видалені.")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Очистити")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
        }
    )
}