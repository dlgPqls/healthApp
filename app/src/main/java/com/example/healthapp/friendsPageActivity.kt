package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class friendsPageActivity : AppCompatActivity() {

    lateinit var btnLV:Button

    lateinit var friendName:TextView
    lateinit var fwork:TextView
    lateinit var fwater:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_page)


        btnLV = findViewById(R.id.btnLV)
        friendName = findViewById(R.id.freindsName)
        fwork = findViewById(R.id.fwork)
        fwater = findViewById(R.id.fwater)

        var work  = intent.getStringExtra("fwork")
        var water = intent.getStringExtra("fwater")
        var objWork = intent.getStringExtra("objWork")
        var objWater = intent.getStringExtra("objWater")


        friendName.text = intent.getStringExtra("fName")
        fwork.text = work+"/"+objWork
        fwater.text = water+"/"+objWater

        btnLV.setOnClickListener {
            onBackPressed()
        }
    }
}