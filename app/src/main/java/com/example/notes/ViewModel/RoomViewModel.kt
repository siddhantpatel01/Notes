package com.example.notes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ROOM_DB.myDatabase
import com.example.notes.repository.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) :AndroidViewModel(application) {
    val allNotes : LiveData<List<Notes>>
    private val repository  : NoteRepo
    init {
        val dao = myDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepo(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(notes)
    }

    fun insertNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(notes)
    }
    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.updateNotes(notes)
    }

    fun allNotes(notes: String?) {
        repository.allNotes
    }
}