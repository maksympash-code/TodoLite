package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    if (selected) {
        Button(
            onClick = onClick
        ) {
            Text(text)
        }
    }
    else {
        OutlinedButton(
            onClick = onClick
        ) {
            Text(text)
        }
    }
}