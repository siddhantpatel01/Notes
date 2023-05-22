package com.example.notes
import android.content.Intent
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.adapter.Notes_Items_Recycler_View_Adapter
import com.example.notes.adapter.iNotesRVAdapter
import com.example.notes.ROOM_DB.Notes
import com.example.notes.ViewModel.RoomViewModel
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), iNotesRVAdapter,  PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RoomViewModel
    private lateinit var myAdapter: Notes_Items_Recycler_View_Adapter
    lateinit var  note:Notes

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

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        setSupportActionBar(binding.toolbar)
        setRecyclerview()

        binding.fab.setOnClickListener {
            startActivity(Intent(this, Notes_layout_Activity::class.java))
        }


       /* binding.searchBar.setOnSearchActionListener(object : SearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {
                // Handle search state change
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                // Handle search query submission
            }

            override fun onButtonClicked(buttonCode: Int) {
                // Handle search bar button clicks
            }
        })


        binding.searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Handle search query text change
                performSearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
            }

            override fun onQueryTextSubmit(query: String?) {
                // Handle search query submission
                performSearch(query.orEmpty())
            }
        })*/
    }

    private fun performSearch(query: String) {
        // Implement your search logic here
        // You can perform a network request, query a local database, etc.
        // Display the search results in your UI
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun setRecyclerview() {
        binding.recyclerViewItems.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        myAdapter = Notes_Items_Recycler_View_Adapter(this, this)
        binding.recyclerViewItems.adapter = myAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings menu item click
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun OnItemClicked(notes: Notes, cardview: CardView) {
        contextmenu(cardview)
        note= notes

    }

    private fun contextmenu(cardview: CardView) {
        val contextmenu = PopupMenu(this,cardview)
        contextmenu.setOnMenuItemClickListener(this)
        contextmenu.inflate(R.menu.deleteitem)
        contextmenu.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
       if(item?.itemId==R.id.delete){
           viewModel.deleteNote(note)
           return true
       }
        return false
    }

//    override fun OnItemClicked(notes: Notes) {
//        viewModel.deleteNote(notes)
//    }
}


