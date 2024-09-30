package com.example.notes.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.navigation.enums.Routes
import com.example.notes.navigation.interfaces.TabItem

@Composable
fun BottomBar(
    onNavigate: (String) -> Unit
) {
    val heroesTab = TabItem(
        title = Routes.Favorites.title,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite
    )

    val comicsTab = TabItem(
        title = Routes.Home.title,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    )

    val profileTab = TabItem(
        title = Routes.RecentlyDeleted.title,
        unselectedIcon = Icons.Outlined.Delete,
        selectedIcon = Icons.Filled.Delete
    )

    val tabs = listOf(heroesTab, comicsTab, profileTab)

    TabsView(tabs = tabs, onNavigate = onNavigate)
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(onNavigate = {})
}