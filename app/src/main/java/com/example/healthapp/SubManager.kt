package com.example.healthapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SubManager (context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE IF NOT EXISTS subTBL(DATE TEXT, NAME TEXT PRIMARY KEY, RUN INTEGER, WATER INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}