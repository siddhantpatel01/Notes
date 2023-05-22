package com.example.notes.ROOM_DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert( notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("Select * from notes order by id ASC")
     fun getAllNotes(): LiveData<List<Notes>>  // make live data to observe changes in list

     @Update
     suspend fun update(notes: Notes)
}
