package com.example.notes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyDeletedViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val notesDatabase = NotesDatabase.getDatabase(context)
    private val notesDao = notesDatabase.noteDao()

    val recentlyDeletedNotes = notesDao.getRecentlyDeletedNotes().asFlow()

    fun restoreNote(noteId: Int) {
        viewModelScope.launch {
            notesDao.restoreNote(noteId)
        }
    }
}