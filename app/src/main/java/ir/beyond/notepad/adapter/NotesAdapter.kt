package ir.beyond.notepad.adapter

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.databinding.ListItemNotesBinding

class NotesAdapter(
    private val context: Activity ,
    private val allData : ArrayList<RecyclerNotesModel>
    ):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            ListItemNotesBinding.inflate(context.layoutInflater , parent , false)
        )

    override fun getItemCount(): Int = allData.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.setData(allData[position])
    }

    inner class NotesViewHolder(
        private val binding : ListItemNotesBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun setData(data : RecyclerNotesModel){
            binding.txtTitleNotes.text = data.title

        }

    }

}