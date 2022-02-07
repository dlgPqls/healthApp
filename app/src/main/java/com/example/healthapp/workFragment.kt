package com.example.healthapp

import android.content.Context
import android.database.Cursor
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
/**
 * A simple [Fragment] subclass.
 * Use the [workFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class workFragment : Fragment() {
    lateinit var workPercent : TextView
    lateinit var workText : TextView
    lateinit var editHour: EditText
    lateinit var editMinute: EditText
    lateinit var workButton: Button
    lateinit var mainActivity: MainActivity

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

        var dbManager: DBManager
        var subManager: SubManager
        var wdatabase : SQLiteDatabase
        var database : SQLiteDatabase
        var cursor1: Cursor
        var cursor2 : Cursor
        var todaytime = 1
        var goal = 5

        dbManager = DBManager(requireContext(),"guruTBL",null,2)
        subManager = SubManager(requireContext(),"subTBL",null,2)
        wdatabase = dbManager.writableDatabase
        database = subManager.writableDatabase
        var id = arguments?.getString("ID")
        cursor1 = database.rawQuery("SELECT * FROM subTBL WHERE NAME = '" + id + "';",null)
        cursor2 = wdatabase.rawQuery("SELECT * FROM guruTBL WHERE gName = '" + id + "';",null)

        workPercent = view.findViewById(R.id.workPercent)
        workText = view.findViewById(R.id.workText)
        editHour = view.findViewById(R.id.editHour)
        editMinute = view.findViewById(R.id.editMinute)
        workButton = view.findViewById(R.id.workButton)

        if(cursor1.moveToNext()){
            todaytime = cursor1.getInt(cursor1.getColumnIndex("RUN"))
        }
        if(cursor2.moveToNext()){
            goal = cursor2.getInt(cursor2.getColumnIndex("RUN"))
        }
        cursor1.close()
        cursor2.close()

        var per = ((todaytime.toDouble()/goal) * 100).toInt()
        var barChart: BarChart = view.findViewById(R.id.chart2) // barChart 생성

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1.2f,20.0f))
        entries.add(BarEntry(2.2f,100.0f))
        entries.add(BarEntry(3.2f,30.0f))
        entries.add(BarEntry(4.2f,90.0f))
        entries.add(BarEntry(5.2f,70.0f))
        entries.add(BarEntry(6.2f,30.0f))
        entries.add(BarEntry(7.2f,120.0f))

        barChart.run {
            description.isEnabled = false // 차트 옆에 별도로 표기되는 description을 안보이게 설정 (false)
            setMaxVisibleValueCount(7) // 최대 보이는 그래프 개수를 7개로 지정
            setPinchZoom(false) // 핀치줌(두손가락으로 줌인 줌 아웃하는것) 설정
            setDrawBarShadow(false) //그래프의 그림자
            setDrawGridBackground(false)//격자구조 넣을건지
            axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                axisMaximum = 181f //180 위치에 선을 그리기 위해 181f로 맥시멈값 설정
                axisMinimum = 0f // 최소값 0
                granularity = 30f // 30 단위마다 선을 그리려고 설정.
                setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
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
        set.color = ContextCompat.getColor(requireActivity()!!,R.color.teal_700) // 바 그래프 색 설정

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f //막대 너비 설정
        barChart.run {
            this.data = data //차트의 데이터를 data로 설정해줌.
            setFitBars(true)
            invalidate()
        }

        //로그인페이지에서 회원 id 값 intent로 받아와서 sql문으로 넘기면 될 듯. 해당 id의 운동량 목표, 물 섭취량 목표 행에 값 넣기

        workButton.setOnClickListener{
            var workTime = (editHour.text.toString().toInt()*60) + (editMinute.text.toString().toInt())
            wdatabase = dbManager.writableDatabase
            wdatabase.execSQL("UPDATE mainTBL SET RUN ='"+ workTime + "' WHERE gName = '" + id + "';"  )
            wdatabase.close()
        }

        mainActivity.findViewById<TextView>(R.id.workPercent).text = "$per"
        mainActivity.findViewById<TextView>(R.id.workText).text = "오늘 하루 " + "$todaytime" +"분 운동했어요"
    }

    inner class MyXAxisFormatter : ValueFormatter() {
        private val days = arrayOf("일","월","화","수","목","금","토")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()-1) ?: value.toString()
        }
    }

    override fun onStart() {
        super.onStart()

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