package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ViewModel.RoomViewModel
import com.example.notes.databinding.ActivityDisplayNotesBinding


class DisplayNotes : AppCompatActivity() {
    private lateinit var viewModel: RoomViewModel
    private lateinit var binding: ActivityDisplayNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_notes)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[RoomViewModel::class.java]

        binding.titleDisplay.setText(intent.getStringExtra("Title")).toString()
        binding.notesFieldDisplay.setText(intent.getStringExtra("Text"))
        val itemId = intent.getStringExtra("id")?.toLong() // Parse the ID as Long

        binding.update.setOnClickListener {
            val noteTextDisplay = binding.notesFieldDisplay.text.toString()
            val noteTitleDisplay = binding.titleDisplay.text.toString()

            if (noteTextDisplay.isNotEmpty() && noteTitleDisplay.isNotEmpty()) {
                val updatedNote = Notes(noteTextDisplay, noteTitleDisplay)
                updatedNote.id =
                    (itemId ?: 0L).toInt() // Assign the correct ID for the updated note
                viewModel.updateNote(updatedNote)
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
