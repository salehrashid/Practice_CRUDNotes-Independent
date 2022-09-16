package com.app.practicecrud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.practicecrud.R
import com.app.practicecrud.database.Notes
import com.app.practicecrud.databinding.RowItemsNoteBinding

class NotesRecyclerViewAdapter(
    private val noteList: List<Notes>,
    private val clickListener: (Notes) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowItemsNoteBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_items_note, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(noteList[position], clickListener)
    }

    override fun getItemCount(): Int = noteList.size
}

class MyViewHolder(val binding: RowItemsNoteBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(notes: Notes, clickListener: (Notes) -> Unit){
        binding.nameTextView.text = notes.note
        binding.nameTextView.setOnClickListener {
            clickListener(notes)
        }
    }
}