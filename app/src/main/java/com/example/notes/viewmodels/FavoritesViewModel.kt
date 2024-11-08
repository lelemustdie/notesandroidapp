package com.example.notes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Note
import com.example.notes.data.NotesDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val notesDatabase = NotesDatabase.getDatabase(context)
    private val notesDao = notesDatabase.noteDao()

    val favoriteNotes = notesDao.getFavoriteNotes().asFlow()


    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesDatabase.noteDao().deleteNote(note.noteId)
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            notesDatabase.noteDao().toggleFavoriteNote(note.noteId, !note.isFavorite)
        }
    }
}