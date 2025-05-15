package com.DanielTownley.lostandfound

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "lost_found.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "items"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TYPE = "type" // Lost or Found
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_DATE = "date"
        const val COLUMN_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_DATE TEXT,
                $COLUMN_LOCATION TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertItem(name: String, type: String, description: String, phone: String, date: String, location: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_TYPE, type)
        values.put(COLUMN_DESCRIPTION, description)
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_LOCATION, location)

        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllItems(): Cursor {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        return db.rawQuery(query, null)
    }

    fun getAllItemsList(): List<Item> {
        val items = mutableListOf<Item>()
        val cursor = getAllItems()

        if (cursor.moveToFirst()) {
            do {
                val item = Item(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                    date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION))
                )
                items.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items
    }

    fun deleteItem(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}

