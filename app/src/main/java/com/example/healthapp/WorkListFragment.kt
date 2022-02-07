package com.example.healthapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.R
import com.example.healthapp.MyCustomDialog
import com.example.healthapp.MyCustomDialogInterface
import com.example.healthapp.RecordAdapter
import com.example.healthapp.Record
import com.example.healthapp.RecordViewModel
import com.example.healthapp.databinding.FragmentWorkListBinding
import java.util.*

class WorkListFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentWorkListBinding? = null
    private val recordViewModel: RecordViewModel by viewModels() // 뷰모델 연결
    private val adapter : RecordAdapter by lazy { RecordAdapter(recordViewModel) } // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // 상단 메뉴 추가
//        setHasOptionsMenu(true)
        // 뷰바인딩
        binding = FragmentWaterListBinding.inflate(inflater,container,false)

        adapter.setHasStableIds(true)

        binding!!.recordRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.recordRecyclerView.adapter = adapter

        recordViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        binding!!.dialogButton.setOnClickListener {
            onFabClicked()
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

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DATE)

        val record = Record(0,false,content, year, month, day)
        recordViewModel.addRecord(record)
        Toast.makeText(activity,"추가", Toast.LENGTH_SHORT).show()
    }
}