package ua.knu.maksym_pashchenko.todolite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

@Composable
fun TodoStats(
    totalTasks: Int,
    completedTasks: Int,
    modifier: Modifier = Modifier
) {
    val statusText = when {
        totalTasks == 0 -> "Список порожній"
        totalTasks == completedTasks -> "Усі задачі виконано \uD83D\uDD25"
        else -> "Продовжуй працювати"
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(
                text = "Усього задач: $totalTasks",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Виконано: $completedTasks",
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = statusText,
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TodoStatsPreview() {
    TodoLiteTheme {
        TodoStats(
            totalTasks = 3,
            completedTasks = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoStatsEmptyPreview() {
    TodoLiteTheme {
        TodoStats(
            totalTasks = 0,
            completedTasks = 0
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodoStatsCompletedPreview() {
    TodoLiteTheme {
        TodoStats(
            totalTasks = 3,
            completedTasks = 3
        )
    }
}