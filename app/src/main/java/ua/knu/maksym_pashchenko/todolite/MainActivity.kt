package ua.knu.maksym_pashchenko.todolite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.knu.maksym_pashchenko.todolite.ui.theme.TodoLiteTheme

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
fun TodoHomeScreen(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Todo Lite",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            text = "Мій перший Android pet-project",
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = {},
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
        ) {
            Text(
                text = "Add task",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TodoHomeScreenPreview() {
    TodoLiteTheme {
        TodoHomeScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}