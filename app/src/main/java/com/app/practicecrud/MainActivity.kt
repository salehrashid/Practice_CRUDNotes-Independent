package com.app.practicecrud

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.practicecrud.adapter.NotesRecyclerViewAdapter
import com.app.practicecrud.database.Notes
import com.app.practicecrud.database.NotesDatabase
import com.app.practicecrud.databinding.ActivityMainBinding
import com.app.practicecrud.repository.NoteRepository
import com.app.practicecrud.viewmodel.NoteViewModel
import com.app.practicecrud.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = NotesDatabase.getInstance(application).notesDAO
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)

        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        binding.myViewModel = noteViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        displayNoteList()
    }

    private fun displayNoteList() {
        noteViewModel.notes.observe(this, Observer {
            Log.d(TAG, "displayNoteList: $it")
            binding.noteRecyclerView.adapter = NotesRecyclerViewAdapter(it){selectedItem: Notes ->
                listItemClicked(
                    selectedItem
                )
            }
        })
    }

    private fun listItemClicked(notes: Notes) {
        noteViewModel.iniUpdateAndDelete(notes)
    }
}