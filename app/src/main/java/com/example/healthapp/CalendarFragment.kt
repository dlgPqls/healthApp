package com.example.healthapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.MyCustomDialog
import com.example.healthapp.MyCustomDialogInterface
import com.example.healthapp.RecordAdapter
import com.example.healthapp.databinding.FragmentCalendarBinding
import com.example.healthapp.Record
import com.example.healthapp.RecordViewModel

class CalendarFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentCalendarBinding? = null
    private val recordViewModel: RecordViewModel by viewModels()
    private val adapter : RecordAdapter by lazy { RecordAdapter(recordViewModel) }

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalendarBinding.inflate(inflater,container,false)

        adapter.setHasStableIds(true)

        binding!!.calendarRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.calendarRecyclerview.adapter = adapter


        binding!!.calendarView.setOnDateChangeListener { _, year, month, day ->
            this.year = year
            this.month = month+1
            this.day = day

            binding!!.calendarDateText.text = "${this.year}/${this.month}/${this.day}"

            recordViewModel.readDateData(this.year,this.month,this.day)
        }

        recordViewModel.readAllData.observe(viewLifecycleOwner, {
            recordViewModel.readDateData(year, month, day)
        })

        recordViewModel.currentData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            Log.d("test5", "onCreateView: gg")
        })

        binding!!.calendarDialogButton.setOnClickListener {
            if(year == 0) {
                Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                onFabClicked()
            }
        }

        return binding!!.root
    }

    private fun onFabClicked(){
        val myCustomDialog = MyCustomDialog(activity!!,this)
        myCustomDialog.show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOkButtonClicked(content: String) {
        // 선택된 날짜로 메모를 추가해줌
        val record = Record(0,false, content, year, month, day)
        recordViewModel.addRecord(record)
        Toast.makeText(activity, "추가", Toast.LENGTH_SHORT).show()
    }
}