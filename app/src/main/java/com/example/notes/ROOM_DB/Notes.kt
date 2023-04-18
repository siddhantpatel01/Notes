package com.example.notes.ROOM_DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Notes(@ColumnInfo(name = "text") val text : String) {
    @PrimaryKey(autoGenerate = true) var id =0

}