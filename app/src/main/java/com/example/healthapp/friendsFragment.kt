package com.example.healthapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
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
        var dbManager: DBManager
        var database : SQLiteDatabase
        var cursor: Cursor

        var subManager:SubManager
        var subDatabase:SQLiteDatabase
        var subCursor:Cursor

        dbManager = DBManager(requireContext(),"guruTBL",null,2)
        database = dbManager.readableDatabase
        cursor = database.rawQuery("SELECT * FROM guruTBL;", null)

        subManager = SubManager(requireContext(),"subTBL",null,2)
        subDatabase = subManager.readableDatabase

        var name:String
        var work:Int
        var water:Int
        var objWork:Int
        var objWater:Int

        while (cursor.moveToNext()){
            name = cursor.getString(0).toString()
            objWater = cursor.getInt(3)
            objWork = cursor.getInt(2)
            subCursor = subDatabase.rawQuery("SELECT * FROM subTBL WHERE NAME = '" + name + "';",null)
            water = subCursor.getInt(3)
            work = subCursor.getInt(2)
            profilList.add(profiles(name,water, work,objWork,objWater))
        }

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
                intent.putExtra("objWork",profilList[position].objWork.toString())
                intent.putExtra("objWater",profilList[position].objWater.toString())

                startActivity(intent)
            }

        })

        cursor.close()

    }

}