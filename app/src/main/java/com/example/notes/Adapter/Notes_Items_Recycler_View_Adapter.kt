package com.example.notes.Adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.R
import com.example.notes.ROOM_DB.Notes

class Notes_Items_Recycler_View_Adapter(val context: Context, val listner: iNotesRVAdapter) :
    RecyclerView.Adapter<Notes_Items_Recycler_View_Adapter.NotesViewHolder>() {

    val allnotes = ArrayList<Notes>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview = itemView.findViewById<TextView>(R.id.notes_item)
        val titless = itemView.findViewById<TextView>(R.id.titlefields)
        val delete_Button = itemView.findViewById<TextView>(R.id.delete_Notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewholder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_items, parent, false)
        )
        viewholder.delete_Button.setOnClickListener {/*delete notes */
            listner.OnItemClicked(allnotes[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return allnotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentnote = allnotes[position]
        holder.textview.text = currentnote.text
        holder.titless.text = currentnote.title


    }


    fun updateList(updatedList: List<Notes>) {
        allnotes.clear()
        allnotes.addAll(updatedList)
        notifyDataSetChanged()
    }
}

interface iNotesRVAdapter {
    fun OnItemClicked(notes: Notes)
}