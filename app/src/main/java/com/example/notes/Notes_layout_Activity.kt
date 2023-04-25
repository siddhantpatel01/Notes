package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
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
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[RoomViewModel::class.java]

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark
        window.statusBarColor = ContextCompat.getColor(this,R.color.white)// set status background white


    }

    fun saveData(view: View) {  // this is a save method and on click method in xml layout
        val noteText = binding.notesField.text.toString()  //  getting the data from view
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))  // insert the data in room db
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show()  // display toast
            finish()// to finish activity
        }
    }
}