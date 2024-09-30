package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): LiveData<List<Note>>

    @Query("UPDATE note SET body = :body, title = :title WHERE noteId = :noteId")
    suspend fun updateNote(noteId: Int, body: String, title: String)

    @Query("UPDATE note SET isFavorite =:isFavorite WHERE noteId = :noteId")
    suspend fun toggleFavoriteNote(noteId: Int, isFavorite: Boolean)
}