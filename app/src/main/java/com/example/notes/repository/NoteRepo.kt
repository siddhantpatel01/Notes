package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.ROOM_DB.NoteDao
import com.example.notes.ROOM_DB.Notes

class NoteRepo(private val noteDao: NoteDao) {
    val allNotes : LiveData<List<Notes>> = noteDao.getAllNotes()
    suspend fun insert(notes: Notes){
        noteDao.insert(notes)
    }
    suspend fun delete(notes: Notes){
        noteDao.delete(notes)
    }
    suspend fun updateNotes(notes: Notes){
        noteDao.update(notes)
    }
}