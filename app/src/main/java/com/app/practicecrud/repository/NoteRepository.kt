package com.app.practicecrud.repository

import com.app.practicecrud.database.Notes
import com.app.practicecrud.database.NotesDAO

class NoteRepository (private val dao: NotesDAO){
    val notes = dao.getAllNotes()

    suspend fun insert(notes: Notes){
        dao.insertNote(notes)
    }

    suspend fun update(notes: Notes){
        dao.updateNote(notes)
    }

    suspend fun delete(notes: Notes){
        dao.deleteNote(notes)
    }

    suspend fun deleteAll(){
        dao.deleteAllNotes()
    }
}