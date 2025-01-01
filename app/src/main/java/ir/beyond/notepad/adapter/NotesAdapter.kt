package ir.beyond.notepad.adapter

import android.app.Activity
import android.content.Context
import ir.beyond.notepad.databinding.ListItemNotesBinding

class NotesAdapter(
    private val context: Activity ,
    private val allData : ArrayList<RecyclerNotesModel>
    ) {

    inner class NotesViewHolder(private val view : ListItemNotesBinding){

    }

}