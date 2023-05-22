package com.example.notes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.DisplayNotes
import com.example.notes.R
import com.example.notes.ROOM_DB.Notes
class Notes_Items_Recycler_View_Adapter(val context: Context, val listner: iNotesRVAdapter) :
    RecyclerView.Adapter<Notes_Items_Recycler_View_Adapter.NotesViewHolder>() {

    val allnotes = ArrayList<Notes>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview = itemView.findViewById<TextView>(R.id.notes_item)
        val titless = itemView.findViewById<TextView>(R.id.titlefields)
        val delete_Button = itemView.findViewById<TextView>(R.id.delete_Notes)
        val layout = itemView.findViewById<CardView>(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.notes_items, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return allnotes.size
    }
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentnote = allnotes[position]
        holder.textview.text = currentnote.text
        holder.titless.text = currentnote.title
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "clicked position $position", Toast.LENGTH_SHORT).show()
            // val data = myDatabase.getDatabase(context).getNoteDao().getAllNotes()
            // Create an Intent object and specify the destination activity.
            // Create an Intent object and specify the destination activity.
            val intent = Intent(context, DisplayNotes::class.java)
            intent.putExtra("Text", currentnote.text)
            intent.putExtra("Title", currentnote.title)
            intent.putExtra("id", currentnote.id.toString())
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            listner.OnItemClicked(allnotes[holder.adapterPosition],holder.layout)
            true
        }
    }
    fun updateList(updatedList: List<Notes>) {
        allnotes.clear()
        allnotes.addAll(updatedList)
        notifyDataSetChanged()
    }
}
interface iNotesRVAdapter {
    fun OnItemClicked(notes: Notes,cardview:CardView)

}