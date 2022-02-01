package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Paint
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var secondIntent: Button
    lateinit var mainIntent: Button
    lateinit var dbManager : DBManager
    lateinit var name : EditText
    lateinit var pw : EditText
    lateinit var database: SQLiteDatabase
    lateinit var sql : String
    lateinit var cursor : Cursor
    lateinit var str_name : String
    lateinit var str_pw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        secondIntent = findViewById(R.id.btn_loginregister)
        mainIntent = findViewById(R.id.btn_loginlogin)
        name = findViewById(R.id.login_name)
        pw = findViewById(R.id.login_pw)


        dbManager = DBManager(this,"guruTBL",null,2)
        database = dbManager.writableDatabase


        secondIntent.setOnClickListener {
            var intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }

        mainIntent.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)

                startActivity(intent)


            }
        }
    }
