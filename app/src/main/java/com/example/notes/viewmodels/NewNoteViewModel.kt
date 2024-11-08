package com.example.notes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Note
import com.example.notes.data.NotesDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val notesDatabase = NotesDatabase.getDatabase(context)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry

    private val _noteCreated = MutableStateFlow(false)
    val noteCreated: StateFlow<Boolean> = _noteCreated

    fun createNote(title: String, body: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val newNote = Note(
                    title = title,
                    body = body,
                    isFavorite = false
                )
                notesDatabase.noteDao().createNote(newNote)
                _noteCreated.value = true
                _showRetry.value = false
            } catch (e: Exception) {
                _showRetry.value = true
            } finally {
                _loading.value = false
            }
        }
    }
}