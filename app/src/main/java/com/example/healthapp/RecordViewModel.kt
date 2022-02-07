package com.example.healthapp

import android.app.Application
import androidx.lifecycle.*
import com.example.healthapp.RecordDatabase
import com.example.healthapp.Record
import com.example.healthapp.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
class RecordViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Record>>
    val readDoneData : LiveData<List<Record>>
    private val repository : RecordRepository

    private var _currentData = MutableLiveData<List<Record>>()
    val currentData : LiveData<List<Record>>
        get() = _currentData

    init{
        val recordDao = RecordDatabase.getDatabase(application)!!.recordDao()
        repository = RecordRepository(recordDao)
        readAllData = repository.readAllData.asLiveData()
        readDoneData = repository.readDoneData.asLiveData()
    }

    fun addRecord(record : Record){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecord(record)
        }
    }

    fun updateRecord(record : Record){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecord(record)
        }
    }

    fun deleteRecord(record : Record){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecord(record)
        }
    }

    fun readDateData(year : Int, month : Int, day : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val tmp = repository.readDateData(year, month, day)
            _currentData.postValue(tmp)
        }
    }

}