package ir.beyond.notepad.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.R
import ir.beyond.notepad.adapter.NotesAdapter
import ir.beyond.notepad.adapter.RecycleBinAdapter
import ir.beyond.notepad.data.DBHelper
import ir.beyond.notepad.data.dao.Notes_Dao
import ir.beyond.notepad.databinding.ActivityRecycleBinBinding

class RecycleBinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecycleBinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initRecycler(){


       val dao = Notes_Dao(DBHelper(this))
        val adapter = RecycleBinAdapter(this , dao)

        binding.recyclerNotes.layoutManager = LinearLayoutManager(
            this , RecyclerView.VERTICAL , false)
        binding.recyclerNotes.adapter = adapter
    }

}