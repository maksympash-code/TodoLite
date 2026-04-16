package ua.knu.maksym_pashchenko.todolite.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoInputSection
import ua.knu.maksym_pashchenko.todolite.presentation.components.TodoList
import ua.knu.maksym_pashchenko.todolite.presentation.ui.theme.TodoLiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoLiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoHomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TodoHomeScreen(modifier: Modifier = Modifier) {
    val tasks = listOf("Buy milk", "Study Kotlin", "Read Android docs")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Todo Lite",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TodoInputSection()
        TodoList(tasks = tasks)
    }
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreen()
    }
}