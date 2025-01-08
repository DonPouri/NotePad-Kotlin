package ir.beyond.notepad.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.beyond.notepad.adapter.NotesAdapter
import ir.beyond.notepad.databinding.ActivityMainBinding
import ir.beyond.notepad.data.DBHelper
import ir.beyond.notepad.data.dao.Notes_Dao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dao : Notes_Dao
    private lateinit var adapter  : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        binding.imgAddNotes.setOnClickListener {
            val intent = Intent(this , AddNotesActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val data = dao.getNotesForRecycler(DBHelper.FALSE_STATE)
        adapter.changeData(data)
    }

    private fun initRecycler(){

        dao = Notes_Dao(DBHelper(this))
        val data = dao.getNotesForRecycler(DBHelper.FALSE_STATE)
        adapter = NotesAdapter(this , data , dao)

        binding.recyclerNotes.layoutManager = LinearLayoutManager(
            this , RecyclerView.VERTICAL , false)
        binding.recyclerNotes.adapter = adapter
    }
}