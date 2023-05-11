package com.example.notes.ROOM_DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "Title")
    val title: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)