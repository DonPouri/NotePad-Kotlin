package ir.beyond.notepad.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.R
import ir.beyond.notepad.data.DBHelper
import ir.beyond.notepad.data.dao.Notes_Dao
import ir.beyond.notepad.data.model.RecyclerNotesModel
import ir.beyond.notepad.databinding.ListItemNotesBinding
import ir.beyond.notepad.ui.AddNotesActivity

class NotesAdapter(
    private val context: Context ,
    private val dao : Notes_Dao
    ):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var allData : ArrayList<RecyclerNotesModel>

    init {
        allData = dao.getNotesForRecycler(DBHelper.FALSE_STATE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            ListItemNotesBinding.inflate(LayoutInflater.from(context) , parent , false)
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
                        val resualt = dao.editNotes(data.id , DBHelper.TRUE_STATE)
                        if (resualt) {
                            showText("note deleted")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        }else
                            showText("not deleted")
                    }
                    .setPositiveButton("no"){_ , _ ->}
                    .create()
                    .show()
            }
            binding.root.setOnClickListener {
                val intent = Intent(context , AddNotesActivity::class.java)
                intent.putExtra("notesis" , data.id)
                context.startActivity(intent)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(data:ArrayList<RecyclerNotesModel>){
            allData = data
            notifyDataSetChanged()
    }
    private fun showText(text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}