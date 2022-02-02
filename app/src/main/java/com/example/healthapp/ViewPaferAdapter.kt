package com.example.healthapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.healthapp.CalendarFragment
import com.example.healthapp.WorkListFragment
import com.example.healthapp.WaterListFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WaterListFragment()
            1 -> CalendarFragment()
            else -> WorkListFragment()
        }
    }
}