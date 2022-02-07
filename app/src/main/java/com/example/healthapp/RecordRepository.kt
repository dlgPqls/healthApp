package com.example.healthapp

import com.example.healthapp.RecordDao
import com.example.healthapp.Record
import kotlinx.coroutines.flow.Flow

class RecordRepository(private val recordDao: RecordDao) {
    val readAllData : Flow<List<Record>> = recordDao.readAllData()
    val readDoneData : Flow<List<Record>> = recordDao.readDoneData()

    suspend fun addRecord(record: Record){
        recordDao.run { addRecord(record) }
    }

    suspend fun updateRecord(record: Record){
        recordDao.run { updateRecord(record) }
    }

    suspend fun deleteRecord(record: Record){
        recordDao.run { deleteRecord(record) }
    }

    fun readDateData(year : Int, month : Int, day : Int): List<Record> {
        return recordDao.readDateData(year, month, day)
    }

}