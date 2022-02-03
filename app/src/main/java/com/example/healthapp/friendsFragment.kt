package com.example.healthapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.location.GnssAntennaInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
//import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class friendsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //DB연동
        var profilList = arrayListOf<profiles>()
        var dbManager:DBManager
        var sqlitedb : SQLiteDatabase
        var cursor: Cursor

        dbManager = DBManager(requireContext(),"guruTBL",null,2)
        sqlitedb = dbManager.readableDatabase
        cursor = sqlitedb.rawQuery("SELECT * FROM groupTBL;", null)

        var name:String
        var work:Int
        var water:Int

        while (cursor.moveToNext()){
            name = cursor.getColumnName(0).toString()
            water = 10
            work = 60
            profilList.add(profiles(name,water, work))
        }

        /*val profilList = arrayListOf(
                //mysql에서 값 가져오기 필요!!
                profiles("김솔",100,89),
                profiles("김해빈",90,88),
                profiles("송설연",80,77),
                profiles("이혜빈",70,66)
        )*/

        val rv_profile = getView()?.findViewById<RecyclerView>(R.id.rv_profile)
        rv_profile?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_profile?.setHasFixedSize(true)

        var adapter = profileAdapter(profilList)
        rv_profile?.adapter = adapter

        adapter.setOnItemClickListener(object : profileAdapter.onItemClickListener{
            override fun onItemClilck(position: Int) {
                //intent 만들어서 넘기기
                val intent = Intent(activity,friendsPageActivity::class.java)

                intent.putExtra("fName",profilList[position].name)
                intent.putExtra("fwater",profilList[position].water.toString())
                intent.putExtra("fwork",profilList[position].work.toString())

                startActivity(intent)
            }

        })

    }

}