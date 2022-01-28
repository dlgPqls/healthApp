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
import java.util.*
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
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
        var barChart: BarChart = view.findViewById(R.id.chart1) // barChart 생성

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1.2f,1.0f))
        entries.add(BarEntry(2.2f,7.0f))
        entries.add(BarEntry(3.2f,10.0f))
        entries.add(BarEntry(4.2f,4.0f))
        entries.add(BarEntry(5.2f,8.0f))
        entries.add(BarEntry(6.2f,12.0f))
        entries.add(BarEntry(7.2f,3.0f))

        barChart.run {
            description.isEnabled = false // 차트 옆에 별도로 표기되는 description을 안보이게 설정 (false)
            setMaxVisibleValueCount(7) // 최대 보이는 그래프 개수를 7개로 지정
            setPinchZoom(false) // 핀치줌(두손가락으로 줌인 줌 아웃하는것) 설정
            setDrawBarShadow(false) //그래프의 그림자
            setDrawGridBackground(false)//격자구조 넣을건지
            axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                axisMaximum = 16f //15 위치에 선을 그리기 위해 16f로 맥시멈값 설정
                axisMinimum = 0f // 최소값 0
                granularity = 5f // 5 단위마다 선을 그리려고 설정.
                setDrawLabels(true) // 값 적는거 허용
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(false) // 축 그리기 설정
                axisLineColor = ContextCompat.getColor(context,R.color.design_default_color_secondary_variant) // 축 색깔 설정
                gridColor = ContextCompat.getColor(context,R.color.grey) // 축 아닌 격자 색깔 설정
                textColor = ContextCompat.getColor(context,R.color.darkgrey) // 라벨 텍스트 컬러 설정
                textSize = 13f //라벨 텍스트 크기
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM //X축을 아래에다가 둔다.
                granularity = 1f // 1 단위만큼 간격 두기
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자
                textColor = ContextCompat.getColor(context,R.color.darkgrey) //라벨 색상
                textSize = 12f // 텍스트 크기
                valueFormatter = MyXAxisFormatter() // X축 라벨값(밑에 표시되는 글자) 바꿔주기 위해 설정
            }
            axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
            setTouchEnabled(false) // 그래프 터치해도 아무 변화없게 막음
            animateY(1000) // 밑에서부터 올라오는 애니매이션 적용
            legend.isEnabled = false //차트 범례 설정
        }

        var set = BarDataSet(entries,"DataSet") // 데이터셋 초기화
        set.color = ContextCompat.getColor(requireActivity()!!,R.color.watergraph) // 바 그래프 색 설정

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f //막대 너비 설정
        barChart.run {
            this.data = data //차트의 데이터를 data로 설정해줌.
            setFitBars(true)
            invalidate()
        }

        //val intent = intent
        //id = intent.getStringExtra("intent_name").toString()
        myHelper = workFragment.myDBHelper(mainActivity)

        waterButton.setOnClickListener{
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("UPDATE mainTBL SET mWater ='"+ editAmount.text.toString().toInt() + "' WHERE mName = + 'id';"  )
            sqlDB.close()
        }
    }

    inner class MyXAxisFormatter : ValueFormatter() {
        private val days = arrayOf("일","월","화","수","목","금","토")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()-1) ?: value.toString()
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
