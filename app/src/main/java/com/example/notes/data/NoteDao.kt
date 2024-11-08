package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(note: Note)

    @Query("UPDATE note SET deleted = 1 WHERE noteId = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM note WHERE deleted = 0")
    fun getNotes(): LiveData<List<Note>>

    @Query("UPDATE note SET body = :body, title = :title WHERE noteId = :noteId")
    suspend fun updateNote(noteId: Int, body: String, title: String)

    @Query("UPDATE note SET isFavorite =:isFavorite WHERE noteId = :noteId")
    suspend fun toggleFavoriteNote(noteId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM note WHERE isFavorite = 1 AND deleted = 0")
    fun getFavoriteNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE deleted = 1")
    fun getRecentlyDeletedNotes(): LiveData<List<Note>>
}