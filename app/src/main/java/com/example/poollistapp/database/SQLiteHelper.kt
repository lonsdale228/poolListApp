package com.example.poollistapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.poollistapp.models.Services
import com.example.poollistapp.models.User

class SQLiteHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    fun insertUser(usr: User):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NAME,usr.name)
        contentValues.put(STREET,usr.street)
        contentValues.put(LAST_VISIT,usr.last_visit)
        contentValues.put(PHONE_NUMBER,usr.phone_number)

        val success=db.insert(TBL_CLIENTS,null,contentValues)
        db.close()
        return success
    }

    companion object{
        private const val DATABASE_VERSION=1
        private const val DATABASE_NAME="clients.db"
        private const val TBL_CLIENTS="tbl_clients"
        private const val TBL_SERVICES="tbl_serviceS"
        private const val ID="id"
        private const val NAME="name"
        private const val STREET="street"
        private const val LAST_VISIT="last_visit"
        private const val PHONE_NUMBER="phone_number"
        private const val DATE="date"
        private const val REMINDER="reminder"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblClients=("CREATE TABLE "+ TBL_CLIENTS+ "("+ ID+" INTEGER PRIMARY KEY,"+ NAME+" TEXT,"+ STREET+" TEXT,"+ PHONE_NUMBER+" TEXT UNIQUE,"+ LAST_VISIT+" TEXT"+")")
        val createTblServices=("CREATE TABLE $TBL_SERVICES ($ID INTEGER PRIMARY KEY, $DATE TEXT, $PHONE_NUMBER TEXT UNIQUE, $REMINDER TEXT)")
        db?.execSQL(createTblClients)
        db?.execSQL(createTblServices)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CLIENTS")
        onCreate(db)
    }



    @SuppressLint("Range")
     fun getAllUsers():ArrayList<User>{
        val usrList:ArrayList<User> = ArrayList()
        val selectQuery="SELECT * FROM $TBL_CLIENTS"
        val db=this.readableDatabase

        val cursor:Cursor?

        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var name:String
        var street:String
        var phone_number:String
        var last_visit:String
        if(cursor.moveToFirst()){
            do {
                name=cursor.getString(cursor.getColumnIndex("name"))
                street=cursor.getString(cursor.getColumnIndex("street"))
                phone_number=cursor.getString(cursor.getColumnIndex("phone_number"))
                last_visit=cursor.getString(cursor.getColumnIndex("last_visit"))

                val usr= User(name = name,street=street,phone_number=phone_number,last_visit=last_visit)

                usrList.add(usr)


            }while (cursor.moveToNext())
        }
        return usrList


    }

    @SuppressLint("Range")
    fun getAllServices():ArrayList<Services>{
        val srvList:ArrayList<Services> = ArrayList()
        val selectQuery="SELECT * FROM $TBL_SERVICES"
        val db=this.readableDatabase

        val cursor:Cursor?

        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var date:String
        var phone_number:String
        var reminder:String
        if(cursor.moveToFirst()){
            do {
                date=cursor.getString(cursor.getColumnIndex("date"))
                phone_number=cursor.getString(cursor.getColumnIndex("phone_number"))
                reminder=cursor.getString(cursor.getColumnIndex("reminder"))

                val srv= Services(date=date,phone_number=phone_number, reminder = reminder)

                srvList.add(srv)


            }while (cursor.moveToNext())
        }
        return srvList
    }

    fun updateUser(usr: User):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()

        contentValues.put(NAME,usr.name)
        contentValues.put(STREET,usr.street)
        contentValues.put(PHONE_NUMBER,usr.phone_number)
        contentValues.put(LAST_VISIT,usr.last_visit)

        val success = db.update(TBL_CLIENTS,contentValues,"phone_number="+usr.phone_number,null)
        db.close()
        return success
    }

    fun deleteUser(phone_number:String){
        val db = this.writableDatabase
        val contentValues=ContentValues()

        contentValues.put(PHONE_NUMBER,phone_number)
        db.execSQL("DELETE FROM "+ TBL_CLIENTS+" WHERE "+ PHONE_NUMBER+" LIKE '%"+phone_number+"%'");
        db.close()

    }

    fun deleteService(phone_number:String){
        val db = this.writableDatabase
        val contentValues=ContentValues()

        contentValues.put(PHONE_NUMBER,phone_number)
        db.execSQL("DELETE FROM "+ TBL_SERVICES+" WHERE "+ PHONE_NUMBER+" LIKE '%"+phone_number+"%'");
        db.close()

    }


}