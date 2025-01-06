package ir.beyond.notepad.adapter

import android.app.Activity
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.R
import ir.beyond.notepad.data.model.RecyclerNotesModel
import ir.beyond.notepad.databinding.ListItemNotesBinding

class NotesAdapter(
    private val context: Activity ,
    private var allData : ArrayList<RecyclerNotesModel>
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
            binding.imgDeleteNotesRecycler.setOnClickListener {
                AlertDialog.Builder(ContextThemeWrapper(context , R.style.CustomAlertDialog))
                    .setTitle("delete note")
                    .setMessage("delete note?")
                    .setIcon(R.drawable.ic_delete)
                    .setNegativeButton("yes"){_ , _ ->

                    }
                    .setPositiveButton("no"){_ , _ ->}
                    .create()
                    .show()
            }
        }

    }

    fun changeData(data:ArrayList<RecyclerNotesModel>){
        if (data.size > allData.size){
            allData = data
            notifyItemInserted(allData.size)
        }
    }
    private fun showText(text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}