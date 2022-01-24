package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottom = findViewById<BottomNavigationView>(R.id.bottom)

        bottom.run {
            setOnItemSelectedListener {
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