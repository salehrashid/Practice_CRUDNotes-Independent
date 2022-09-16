package com.app.practicecrud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.practicecrud.database.Notes
import com.app.practicecrud.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    val inputNote = MutableLiveData<String?>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    val notes = repository.notes
    private lateinit var noteToUpdateOrDelete: Notes
    private var isUpdateOrDelete = false

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun iniUpdateAndDelete(notes: Notes) {
        inputNote.value = notes.note
        isUpdateOrDelete = true
        noteToUpdateOrDelete = notes
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Clear"
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            noteToUpdateOrDelete.note = inputNote.value!!
            update(noteToUpdateOrDelete)
        } else {
            val note = inputNote.value!!
            insert(Notes(0, note))
            inputNote.value = ""
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(noteToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun insert(notes: Notes) {
        viewModelScope.launch {
            repository.insert(notes)
        }
    }

    private fun update(notes: Notes) {
        viewModelScope.launch {
            repository.update(notes)

            inputNote.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }

    private fun delete(notes: Notes) {
        viewModelScope.launch {
            repository.delete(notes)

            inputNote.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}