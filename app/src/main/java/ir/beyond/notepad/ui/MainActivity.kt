package ir.beyond.notepad.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.adapter.NotesAdapter
import ir.beyond.notepad.databinding.ActivityMainBinding
import ir.beyond.notepad.db.DBHelper
import ir.beyond.notepad.db.dao.Notes_Dao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgAddNotes.setOnClickListener {
            val intent = Intent(this , AddNotesActivity::class.java)
            startActivity(intent)
        }

        initRecycler()
    }

    private fun initRecycler(){

        val dao = Notes_Dao(DBHelper(this))
        val data = dao.getNotesForRecycler(DBHelper.FALSE_STATE)

        binding.recyclerNotes.layoutManager = LinearLayoutManager(
            this , RecyclerView.VERTICAL , false)
        binding.recyclerNotes.adapter = NotesAdapter(this , data)
    }
}