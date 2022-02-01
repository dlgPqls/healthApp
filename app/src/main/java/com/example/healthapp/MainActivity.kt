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

        class myDBHelper(context: Context) : SQLiteOpenHelper(context,"subTBL",null,2){
            override fun onCreate(db: SQLiteDatabase?) {
                db!!.execSQL("CREATE TABLE subTBL (RUN INTEAGER, WATER INTEAGER);")
            }

            override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
                db!!.execSQL("DROP TABLE IF EXISTS subTBL")
                onCreate(db)
            }

        }

        var bottom = findViewById<BottomNavigationView>(R.id.bottom)

        bottom.run {
            setOnNavigationItemSelectedListener() {
                when (it.itemId) {
                    R.id.tab_mypage -> {
                        val mypageFragment = mypageFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, mypageFragment).commit()
                    }
                    R.id.tab_water -> {
                        val calendarFragment = waterFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.container, calendarFragment).commit()
                    }
                    R.id.tab_work->{
                        val workFragment = workFragment()
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