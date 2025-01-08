package ir.beyond.notepad.data.dao

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import ir.beyond.notepad.data.model.RecyclerNotesModel
import ir.beyond.notepad.data.DBHelper
import ir.beyond.notepad.data.model.DBNotesModel

class Notes_Dao(private val db:DBHelper
) {

    private lateinit var cursor : Cursor
    private val contentValues = ContentValues()

    fun saveNotes(notes : DBNotesModel):Boolean{

        val database = db.writableDatabase
        setContentValues(notes)
        val result =database.insert(DBHelper.NOTES_TABLE , null , contentValues)
        database.close()
        return result>0

    }

    fun editNotes(id:Int , state:String) : Boolean{

        val database = db.writableDatabase
        contentValues.clear()
        contentValues.put(DBHelper.NOTES_DELETE_STATE , state)
        val resualt = database.update(
            DBHelper.NOTES_TABLE ,
            contentValues ,
            "${DBHelper.NOTES_ID} = ?" ,
            arrayOf(id.toString())
        )
        database.close()
        return resualt > 0
    }

    fun editNotes(id:Int , notes:DBNotesModel){

    }

    private fun setContentValues(notes: DBNotesModel) {
        contentValues.clear()
        contentValues.put(DBHelper.NOTES_TITLE ,notes.title)
        contentValues.put(DBHelper.NOTES_DETAIL ,notes.detail)
        contentValues.put(DBHelper.NOTES_DELETE_STATE ,notes.deletestate)
        contentValues.put(DBHelper.NOTES_DATE ,notes.date)
    }



    fun getNotesForRecycler(value:String):ArrayList<RecyclerNotesModel>{

        val database  = db.readableDatabase
        val query = "SELECT ${DBHelper.NOTES_ID}, ${DBHelper.NOTES_TITLE} " +
                "FROM ${DBHelper.NOTES_TABLE}" +
                " WHERE ${DBHelper.NOTES_DELETE_STATE} = ? "
        cursor = database.rawQuery(query, arrayOf(value))
        val data = getDataForRecycler()
        cursor.close()
        database.close()
        return data
    }

    private fun getDataForRecycler(): ArrayList<RecyclerNotesModel> {

        val data = ArrayList<RecyclerNotesModel>()

        try {

            if (cursor.moveToFirst()){
                do{
                    val id = cursor.getInt(getIndext(DBHelper.NOTES_ID))
                    val title = cursor.getString(getIndext(DBHelper.NOTES_TITLE))
                    data.add(RecyclerNotesModel(id , title))
                }while (cursor.moveToNext())
            }

        }catch (e: Exception) {
            Log.e("ERROR" , e.message.toString())
        }
        return data

    }

    fun getIndext(name:String) = cursor.getColumnIndex(name)

}