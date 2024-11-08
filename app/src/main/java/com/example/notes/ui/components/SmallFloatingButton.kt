package com.example.notes.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import com.example.notes.ui.theme.PrimaryPink
import com.example.notes.ui.theme.SecondaryPink

@Composable
fun SmallFloatingButton(onClick: () -> Unit) {
        SmallFloatingActionButton(
            onClick = { onClick() },
            containerColor = SecondaryPink,
            contentColor = PrimaryPink
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }

}