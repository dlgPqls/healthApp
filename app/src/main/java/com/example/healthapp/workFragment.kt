package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.concurrent.timer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [workFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class workFragment : Fragment() {
    private var per = 1;
    private var workTime : Int = 0
    private var many : Int = 10

    lateinit var workPercent : TextView
    lateinit var workText : TextView
    lateinit var editHour: EditText
    lateinit var editMinute: EditText
    lateinit var workButton: Button
    lateinit var mainActivity: MainActivity

    lateinit var myHelper : myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workPercent = view.findViewById(R.id.workPercent)
        workText = view.findViewById(R.id.workText)
        editHour = view.findViewById(R.id.editHour)
        editMinute = view.findViewById(R.id.editMinute)
        workButton = view.findViewById(R.id.workButton)

        //로그인페이지에서 회원 id 값 intent로 받아와서 sql문으로 넘기면 될 듯. 해당 id의 운동량 목표, 물 섭취량 목표 행에 값 넣기

        myHelper = myDBHelper(mainActivity)

        workButton.setOnClickListener{
            workTime = (editHour.text.toString().toInt()*60) + (editMinute.text.toString().toInt())
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("UPDATE mainTBL SET mWork ='"+ workTime + "' WHERE mName = + 'id';"  )
            sqlDB.close()
        }
    }

    class myDBHelper(context: Context) : SQLiteOpenHelper(context, "groupDB", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE mainTBL (mName CHAR(20) PRIMARY KEY, mWork Integer, mWater Integer);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS mainTBL")
            onCreate(db)
        }
    }

    override fun onStart() {
        super.onStart()
        mainActivity.findViewById<TextView>(R.id.workPercent).text = "$per"
        mainActivity.findViewById<TextView>(R.id.workText).text = "오늘 하루 " + "$many" +"분 운동했어요"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment workFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            workFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}