package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_join.*



class JoinActivity : AppCompatActivity() {

    lateinit var Name : EditText
    lateinit var PW : EditText
    lateinit var Register : Button

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        Name = findViewById(R.id.join_name)
        PW = findViewById(R.id.join_pw)

        Register = findViewById(R.id.btn_joinregister)

        dbManager = DBManager(this,"guruTBL",null,2)


        Register.setOnClickListener {
            var str_name : String = Name.text.toString()
            var str_pw : String = PW.text.toString()
            var run : Int = 60
            var water : Int = 10

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO guruTBL VALUES('" +str_name+"', '" +str_pw+"','" +run+"','" +water+"')")
            sqlitedb.close()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }


        btn_joinback.setOnClickListener {
            finish()

        }

    }
}