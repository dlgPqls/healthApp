package com.example.healthapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class waterFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    lateinit var barChart: BarChart
    private val barData = ArrayList<BarData>()
    private var per = 10;
    private var cup = 2;
    lateinit var mainActivity: MainActivity

    lateinit var waterPercent : TextView
    lateinit var waterText : TextView
    lateinit var editAmount: EditText
    lateinit var waterButton: Button

    lateinit var myHelper : workFragment.myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waterPercent = view.findViewById(R.id.waterPercent)
        waterText = view.findViewById(R.id.waterText)
        editAmount = view.findViewById(R.id.editAmount)
        waterButton = view.findViewById(R.id.waterButton)

        //val intent = intent
        //id = intent.getStringExtra("intent_name").toString()
        myHelper = workFragment.myDBHelper(mainActivity)

        waterButton.setOnClickListener{
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("UPDATE mainTBL SET mWater ='"+ editAmount.text.toString().toInt() + "' WHERE mName = + 'id';"  )
            sqlDB.close()
        }
    }

    override fun onStart() {
        super.onStart()
        mainActivity.findViewById<TextView>(R.id.waterPercent).text = "$per"
        mainActivity.findViewById<TextView>(R.id.waterText).text = "오늘 하루 " + "$cup" +"잔의 물을 마셨어요"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water, container, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment waterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            waterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
