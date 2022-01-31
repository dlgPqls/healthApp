package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.*

class friendsPageActivity : AppCompatActivity() {

    lateinit var btnLV:Button

    lateinit var friendName:TextView
    lateinit var fwork:TextView
    lateinit var fwater:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        btnLV = findViewById(R.id.btnLV)
        friendName = findViewById(R.id.freindsName)
        fwork = findViewById(R.id.fwork)
        fwater = findViewById(R.id.fwater)

        friendName.text = intent.getStringExtra("fName")
        fwork.text = intent.getStringExtra("fwork")+"%"
        fwater.text = intent.getStringExtra("fwater")+"%"

        btnLV.setOnClickListener {
            onBackPressed()
        }
    }
}