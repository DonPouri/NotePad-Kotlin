package ir.beyond.notepad.adapter

import android.content.Context
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
import ir.beyond.notepad.databinding.ListItemRecycleBinBinding

class RecycleBinAdapter(
    private val context: Context,
    private val dao: Notes_Dao
) : RecyclerView.Adapter<RecycleBinAdapter.RecyclerViewHolder>() {

    private val allData: ArrayList<RecyclerNotesModel> =
        dao.getNotesForRecycler(DBHelper.TRUE_STATE)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHolder(
            ListItemRecycleBinBinding.inflate(LayoutInflater.from(context), parent, false)
        )

    override fun getItemCount(): Int = allData.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.setData(allData[position])
    }

    inner class RecyclerViewHolder(
        private val binding: ListItemRecycleBinBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: RecyclerNotesModel) {
            binding.txtTitleNotes.text = data.title

            binding.imgDeleteNotesRecycler.setOnClickListener {
                AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomAlertDialog))
                    .setTitle("delete note")
                    .setMessage("delete note?")
                    .setIcon(R.drawable.ic_delete)
                    .setNegativeButton("yes") { _, _ ->
                        val resualt = dao.deleteNotes(data.id)
                        if (resualt) {
                            showText("note deleted")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        } else
                            showText("not deleted")
                    }
                    .setPositiveButton("no") { _, _ -> }
                    .create()
                    .show()
            }
            binding.imgRestore.setOnClickListener {
                AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomAlertDialog))
                    .setTitle("restore note")
                    .setMessage("restore note?")
                    .setIcon(R.drawable.ic_delete)
                    .setNegativeButton("yes") { _, _ ->
                        val resualt = dao.editNotes(data.id, DBHelper.FALSE_STATE)
                        if (resualt) {
                            showText("note resoterd")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        } else
                            showText("not deleted")
                    }
                    .setPositiveButton("no") { _, _ -> }
                    .create()
                    .show()
            }

        }

    }


    private fun showText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
