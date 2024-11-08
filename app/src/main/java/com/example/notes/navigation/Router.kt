package com.example.notes.navigation

import Home
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notes.navigation.enums.Routes
import com.example.notes.screens.Favorites
import com.example.notes.screens.NewNote
import com.example.notes.screens.RecentlyDeleted
import com.example.notes.ui.theme.tenDp

@Composable
fun Router(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.title,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = tenDp)
    ) {

        composable(route = Routes.Favorites.title) {
            Favorites()
        }
        composable(route = Routes.Home.title) {
            Home(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(route = Routes.RecentlyDeleted.title) {
            RecentlyDeleted()
        }
        composable(route = Routes.NewNote.title){
            NewNote(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
    }
}