package com.example.notes.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.R
import com.example.notes.navigation.enums.Routes
import com.example.notes.navigation.interfaces.TabItem

@Composable
fun BottomBar(
    onNavigate: (String) -> Unit
) {
    val favsTab = TabItem(
        title = Routes.Favorites.title,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        text = stringResource(R.string.favorites)
    )

    val homeTab = TabItem(
        title = Routes.Home.title,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        text = stringResource(R.string.home_page)
    )

    val recentlyDeletedTab = TabItem(
        title = Routes.RecentlyDeleted.title,
        unselectedIcon = Icons.Outlined.Delete,
        selectedIcon = Icons.Filled.Delete,
        text = stringResource(R.string.recently_deleted)
    )

    val tabs = listOf(favsTab, homeTab, recentlyDeletedTab)

    TabsView(tabs = tabs, onNavigate = onNavigate)
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(onNavigate = {})
}