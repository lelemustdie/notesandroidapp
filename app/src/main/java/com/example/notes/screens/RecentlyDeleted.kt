package com.example.notes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.R
import com.example.notes.data.Note
import com.example.notes.ui.theme.Grey80
import com.example.notes.ui.theme.SecondaryPink
import com.example.notes.ui.theme.eightDp
import com.example.notes.ui.theme.oneF
import com.example.notes.ui.theme.sixteenDp
import com.example.notes.viewmodels.RecentlyDeletedViewModel

@Composable
fun RecentlyDeleted(recentlyDeletedViewModel: RecentlyDeletedViewModel = hiltViewModel()) {
    val recentlyDeletedNotes by recentlyDeletedViewModel.recentlyDeletedNotes.collectAsState(emptyList())

    if (recentlyDeletedNotes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.nodeleted),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(sixteenDp)) {
            items(recentlyDeletedNotes) { note ->
                NoteCardWithRestoreButton(note, onRestore = {
                    recentlyDeletedViewModel.restoreNote(note.noteId)
                })
                Spacer(modifier = Modifier.height(eightDp))
            }
        }
    }
}

@Composable
fun NoteCardWithRestoreButton(note: Note, onRestore: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(sixteenDp),
        shape = RoundedCornerShape(eightDp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryPink
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(sixteenDp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(oneF)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = eightDp)
                )

                Text(
                    text = note.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = { onRestore() },
                shape = RoundedCornerShape(eightDp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = stringResource(R.string.restore),
                    style = MaterialTheme.typography.titleMedium.copy(color = Grey80),
                )
            }
        }
    }
}
