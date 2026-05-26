package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
        FilterButton(
            "Усі",
            selected = selectedFilter == TaskFilter.ALL,
            onClick = { onFilterSelected(TaskFilter.ALL) }
        )

        FilterButton(
            "Активні",
            selected = selectedFilter == TaskFilter.ACTIVE,
            onClick = { onFilterSelected(TaskFilter.ACTIVE) }
        )

        FilterButton(
            "Виконані",
            selected = selectedFilter == TaskFilter.COMPLETED,
            onClick = { onFilterSelected(TaskFilter.COMPLETED) }
        )
    }
}