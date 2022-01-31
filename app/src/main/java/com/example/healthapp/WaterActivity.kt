package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_water.*

class WaterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water)

        btn_a.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java) // 운동페이지로 이동
            startActivity(intent)
        }
    }
}


