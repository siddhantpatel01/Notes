package com.example.notes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ROOM_DB.myDatabase
import com.example.notes.Rapo.NoteRepo

class RoomViewModel(application: Application) :AndroidViewModel(application) {
    val allNotes : LiveData<List<Notes>>
    init {
        val dao = myDatabase.getDatabase(application).getNoteDao()
        val repository = NoteRepo(dao)
        allNotes = repository.allNotes
    }

}