package ir.beyond.notepad.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.beyond.notepad.R
import ir.beyond.notepad.databinding.ActivityAddNotesBinding
import ir.beyond.notepad.db.DBHelper
import ir.beyond.notepad.db.dao.Notes_Dao
import ir.beyond.notepad.db.model.DBNotesModel

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = Notes_Dao(DBHelper(this))

        binding.btnSave.setOnClickListener {

            val title = binding.edtTitleNote.text.toString()
            val detail = binding.edtDetailNote.text.toString()

            if (title.isNotEmpty()) {

                val notes = DBNotesModel(0, title, detail, "0", getDate())
                val resualt = dao.saveNotes(notes)
                if (resualt){
                    Toast.makeText(this, "saved!", Toast.LENGTH_SHORT).show()
                    finish()
                }else Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

            } else
                Toast.makeText(this, "subject cant be empty", Toast.LENGTH_SHORT).show()

        }
        binding.btnCancel.setOnClickListener {finish()}

    }

    private fun getDate(): String {

        return "test"

    }
}
