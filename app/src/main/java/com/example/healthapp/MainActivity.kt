package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottom = findViewById<BottomNavigationView>(R.id.bottom)
        var id = intent.getStringExtra("Name")

        bottom.run {
            setOnNavigationItemSelectedListener() {
                when (it.itemId) {
                    R.id.tab_mypage -> {
                        val mypageFragment = mypageFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, mypageFragment).commit()
                    }
                    R.id.tab_water -> {
                        val calendarFragment = waterFragment()
                        val bundle = Bundle()
                        bundle.putString("ID", id)
                        calendarFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().replace(R.id.container, calendarFragment).commit()
                    }
                    R.id.tab_work->{
                        val workFragment = workFragment()
                        val bundle = Bundle()
                        bundle.putString("ID", id)
                        workFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().replace(R.id.container, workFragment).commit()
                    }
                    R.id.tab_friends -> {
                        val friendsFragment = friendsFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, friendsFragment).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.tab_mypage
        }

    }
}