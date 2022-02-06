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


class LoginActivity : AppCompatActivity() {
    lateinit var secondIntent: Button
    lateinit var mainIntent: Button
    lateinit var dbManager: DBManager
    lateinit var name: EditText
    lateinit var pw: EditText
    lateinit var maindatabase: SQLiteDatabase
    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        secondIntent = findViewById(R.id.btn_loginregister)
        mainIntent = findViewById(R.id.btn_loginlogin)
        name = findViewById(R.id.login_name)
        pw = findViewById(R.id.login_pw)
        var samepw : String = ""


        dbManager = DBManager(this, "guruTBL", null, 2)
        maindatabase = dbManager.writableDatabase

        secondIntent.setOnClickListener {
            var intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        mainIntent.setOnClickListener {
            var id= name.text.toString()
            cursor = maindatabase.rawQuery("SELECT * from guruTBL WHERE gName = '" + id + "';", null)
            if(cursor.moveToNext()){
                samepw = cursor.getString(cursor.getColumnIndex("gPW"))
            }
            var mypw = pw.text.toString()
            if (samepw.equals(mypw)) {
                var myid = name.text.toString()
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Name", myid)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "아이디 혹은 비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();

            }
        }
    }
}

