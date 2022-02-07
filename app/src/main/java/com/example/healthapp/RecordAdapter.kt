package com.example.healthapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.Record
import com.example.healthapp.UpdateDialog
import com.example.healthapp.UpdateDialogInterface
import com.example.healthapp.RecordViewModel
import com.example.healthapp.databinding.RecordItemBinding

class RecordAdapter(private val recordViewModel: RecordViewModel) : RecyclerView.Adapter<RecordAdapter.MyViewHolder>() {

    private var recordList = emptyList<Record>()

    // 뷰 홀더에 데이터를 바인딩
    class MyViewHolder(private val binding: RecordItemBinding) : RecyclerView.ViewHolder(binding.root),
        UpdateDialogInterface{
        lateinit var record : Record
        lateinit var recordViewModel: RecordViewModel

        fun bind(currentRecord : Record, recordViewModel: RecordViewModel){
            binding.record = currentRecord
            this.recordViewModel = recordViewModel

            // 체크 리스너 초기화 해줘 중복 오류 방지
            binding.recordCheckBox.setOnCheckedChangeListener(null)

            // 메모 체크 시 체크 데이터 업데이트
            binding.recordCheckBox.setOnCheckedChangeListener { _, check ->
                if (check) {
                    record = Record(currentRecord.id, true, currentRecord.content,
                        currentRecord.year, currentRecord.month, currentRecord.day)
                    this.recordViewModel.updateRecord(record)
                }
                else {
                    record = Record(currentRecord.id, false, currentRecord.content,
                        currentRecord.year, currentRecord.month, currentRecord.day)
                    this.recordViewModel.updateRecord(record)
                }
            }

            // 삭제 버튼 클릭 시 메모 삭제
            binding.deleteButton.setOnClickListener {
                recordViewModel.deleteRecord(currentRecord)
            }

            // 수정 버튼 클릭 시 다이얼로그 띄움
            binding.updateButton.setOnClickListener {
                record = currentRecord
                val myCustomDialog = UpdateDialog(binding.updateButton.context,this)
                myCustomDialog.show()
            }
        }

        // 다이얼로그의 결과값으로 업데이트 해줌
        override fun onOkButtonClicked(content: String) {
            val updateRecord = Record(record.id,record.check,content,record.year,record.month,record.day)
            recordViewModel.updateRecord(updateRecord)
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 바인딩 함수로 넘겨줌
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recordList[position],recordViewModel)
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return recordList.size
    }

    // 메모 리스트 갱신
    fun setData(record : List<Record>){
        recordList = record
        notifyDataSetChanged()
    }

    // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}