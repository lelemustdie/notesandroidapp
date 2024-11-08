package com.example.notes.screens

import NoteCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notes.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.ui.theme.eightDp
import com.example.notes.ui.theme.sixteenDp
import com.example.notes.viewmodels.FavoritesViewModel

@Composable
fun Favorites(favoritesViewModel: FavoritesViewModel = hiltViewModel()) {
    val favoriteNotes by favoritesViewModel.favoriteNotes.collectAsState(initial = emptyList())

    if (favoriteNotes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.nofavs),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(sixteenDp)
        ) {
            items(favoriteNotes) { note ->
                NoteCard(
                    note = note,
                    onDelete = { favoritesViewModel.deleteNote(it) },
                    onToggleFavorite = { favoritesViewModel.toggleFavorite(it) }
                )
                Spacer(modifier = Modifier.height(eightDp))
            }
        }
    }
}