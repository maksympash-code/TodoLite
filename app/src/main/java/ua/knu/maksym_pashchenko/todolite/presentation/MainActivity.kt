package ua.knu.maksym_pashchenko.todolite.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ua.knu.maksym_pashchenko.todolite.presentation.screens.TodoHomeScreen
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