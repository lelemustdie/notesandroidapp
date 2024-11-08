package com.example.notes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Note
import com.example.notes.data.NotesDatabase
import com.example.notes.service.ApiImplementation
import com.example.notes.service.UVResponse
import com.example.notes.service.UVResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val service: ApiImplementation,
) : ViewModel() {
    private val notesConnectDatabase = NotesDatabase.getDatabase(context)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _notes = notesConnectDatabase.noteDao().getNotes()
    val notes = _notes.asFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry

    private val _uvIndex = MutableStateFlow(
        UVResponse(
            result = UVResult(
                uv_max = 0.0,
                uv_max_time = ""
            )
        )
    )
    val uvIndex: StateFlow<UVResponse> = _uvIndex.asStateFlow()
    fun saveNote(note: Note) {
        viewModelScope.launch {
            _loading.value = true
            try {
                notesConnectDatabase.noteDao().createNote(note)
                _showRetry.value = false
            } catch (e: Exception) {
                _showRetry.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _loading.value = true
            try {
                notesConnectDatabase.noteDao().deleteNote(note.noteId)
                _showRetry.value = false
            } catch (e: Exception) {
                _showRetry.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            _loading.value = true
            try {
                notesConnectDatabase.noteDao().toggleFavoriteNote(note.noteId, !note.isFavorite)
                _showRetry.value = false
            } catch (e: Exception) {
                _showRetry.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            _loading.value = true
            try {
                notesConnectDatabase.noteDao().updateNote(note.noteId, note.body, note.title)
                _showRetry.value = false
            } catch (e: Exception) {
                _showRetry.value = true
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchUVData() {
        viewModelScope.launch {
            _loading.value = true
            service.getUVIndex(
                context = context,
                onSuccess = {
                    _uvIndex.value = it
                    _showRetry.value = false
                },
                onFail = {
                    _showRetry.value = true
                },
                loadingFinished = {
                    _loading.value = false
                }
            )
        }
    }

    init {
        fetchUVData()
    }

}
