package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ViewModel.RoomViewModel
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.databinding.ActivityNotesLayoutBinding

class Notes_layout_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesLayoutBinding
    private lateinit var viewModel: RoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notes_layout)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[RoomViewModel::class.java]


    }

    fun saveData(view: View) {
        val noteText = binding.notesField.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}