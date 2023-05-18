package com.example.projektniniop

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var context: Context? = context
    companion object{
        private const val DATABASE_NAME = "Gnoms.db"
        private const val DATABASE_VERSION = 1
        private  const val TABLE_NAME = "gnoms"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "gnom_name"
        private const val COLUMN_PRICE = "gnom_price"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_PRICE INTEGER);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addGnom(gnom_name: String?, gnom_price: Int) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, gnom_name)
        cv.put(COLUMN_PRICE, gnom_price)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added successfully !", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}