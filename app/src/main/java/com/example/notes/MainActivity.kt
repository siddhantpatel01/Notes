package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.Adapter.Notes_Items_Recycler_View_Adapter
import com.example.notes.Adapter.iNotesRVAdapter
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ViewModel.RoomViewModel
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), iNotesRVAdapter {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RoomViewModel
    private lateinit var myAdapter: Notes_Items_Recycler_View_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[RoomViewModel::class.java]

        viewModel.allNotes.observe(this, Observer {
            it?.let {
                myAdapter.updateList(it)
            }
        })

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark
        window.statusBarColor =
            ContextCompat.getColor(this, R.color.white)// set status background white

        setSupportActionBar(binding.toolbar)

        setRecyclerview()


        binding.fab.setOnClickListener { view ->
            startActivity(Intent(this, Notes_layout_Activity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun setRecyclerview() {
        //binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        //binding.recyclerViewItems.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewItems.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        myAdapter = Notes_Items_Recycler_View_Adapter(this, this)
        binding.recyclerViewItems.adapter = myAdapter
        myAdapter.notifyDataSetChanged()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun OnItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show()
    }
}
