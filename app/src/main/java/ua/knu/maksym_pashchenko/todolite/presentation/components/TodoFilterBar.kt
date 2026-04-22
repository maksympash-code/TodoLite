package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.viewmodels.TaskFilter

@Composable
fun TodoFilterBar(
    selectedFilter: TaskFilter,
    onFilterSelected: (TaskFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onFilterSelected(TaskFilter.ALL) }
        ) {
            Text(text = "Усі")
        }
        Button(
            onClick = { onFilterSelected(TaskFilter.ACTIVE) }
        ) {
            Text(text = "Активні")
        }
        Button(
            onClick = { onFilterSelected(TaskFilter.COMPLETED) }
        ) {
            Text(text = "Виконані")
        }
    }

}