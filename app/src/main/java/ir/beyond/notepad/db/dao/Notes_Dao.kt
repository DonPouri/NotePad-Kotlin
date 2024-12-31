package ir.beyond.notepad.db.dao

import android.content.ContentValues
import ir.beyond.notepad.db.DBHelper
import ir.beyond.notepad.db.model.DBNotesModel

class Notes_Dao(private val db:DBHelper
) {

    private val contentValues = ContentValues()

    fun saveNotes(notes : DBNotesModel):Boolean{

        val database = db.writableDatabase
        setContentValues(notes)
        val result =database.insert(DBHelper.NOTES_TABLE , null , contentValues)
        database.close()
        return result>0

    }

    private fun setContentValues(notes: DBNotesModel) {
        contentValues.clear()
        contentValues.put(DBHelper.NOTES_TITLE ,notes.title)
        contentValues.put(DBHelper.NOTES_DETAIL ,notes.detail)
        contentValues.put(DBHelper.NOTES_DELETE_STATE ,notes.deletestate)
        contentValues.put(DBHelper.NOTES_DATE ,notes.date)
    }

}