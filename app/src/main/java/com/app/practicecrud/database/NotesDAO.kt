package com.app.practicecrud.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert
    suspend fun insertNote(notes: Notes): Long

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("DELETE FROM note_data_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_data_table")
    fun getAllNotes(): LiveData<List<Notes>>
}