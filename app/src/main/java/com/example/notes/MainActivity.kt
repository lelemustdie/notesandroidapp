package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.notes.navigation.BottomBar
import com.example.notes.navigation.Router
import com.example.notes.ui.components.SmallFloatingButton
import com.example.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NotesTheme {
                Surface {
                    Scaffold(
                        bottomBar = {
                            BottomBar(onNavigate = {
                                navController.navigate(it)
                            })
                        },
//                        floatingActionButton = {
//                            SmallFloatingButton { navController.navigate(newNote) }
//                        }
                    ) { it ->
                        Router(innerPadding = it, navController = navController)
                    }
                }
            }
        }
    }
}