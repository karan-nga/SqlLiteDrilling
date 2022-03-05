package com.rawtooth.demoapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull
import java.lang.Exception

class SQLiteHelper(context: Context):SQLiteOpenHelper(context,DATEBASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private  const val DATABASE_VERSION=1
        private const val DATEBASE_NAME="details.db"
        private const val TBL_DETAILS="tbl_details"
        private  const val ID="id"
        private const val NAME="name"
        private const val EMAIL="email"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTbl=("CREATE TABLE "+ TBL_DETAILS+"("+ ID+" INTEGER PRIMARY KEY,"+ NAME+" TEXT,"+ EMAIL+" TEXT"+")")
        db?.execSQL(createTbl)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db!!.execSQL("DROP TABLE IF EXISTS $TBL_DETAILS")
        onCreate(db)
    }
    fun insertDetails(dts:DetailModel):Long{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(ID,dts.id)
        contentValues.put(NAME,dts.name)
        contentValues.put(EMAIL,dts.email)

        val success=db.insert(TBL_DETAILS,null,contentValues)
        db.close()
        return success
    }
    @SuppressLint("Range")
    fun getAllDetail():ArrayList<DetailModel>{
        val dtsList:ArrayList<DetailModel> =ArrayList()
        val selectQuery="SELECT * FROM $TBL_DETAILS"
        val db=this.readableDatabase
        val cusor:Cursor?
        try{
            cusor=db.rawQuery(selectQuery,null)
        }
        catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var email:String
        if(cusor.moveToFirst()){
            do{
                id = cusor.getInt(cusor.getColumnIndex("id"))
                name=cusor.getString(cusor.getColumnIndex("name"))
                email=cusor.getString(cusor.getColumnIndex("email"))
                val dts=DetailModel(id=id,name=name,email=email)
               dtsList.add(dts)


            }
                while (cusor.moveToNext())
        }
        return  dtsList
    }
}