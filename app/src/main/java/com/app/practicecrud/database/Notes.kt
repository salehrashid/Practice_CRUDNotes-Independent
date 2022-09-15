package com.app.practicecrud.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_data_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notes_id")
    var id : Int,

    @ColumnInfo(name = "notes_catatan")
    var note: String
)
