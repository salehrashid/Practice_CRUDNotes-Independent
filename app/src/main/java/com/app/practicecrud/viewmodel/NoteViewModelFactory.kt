package com.app.practicecrud.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.practicecrud.repository.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}