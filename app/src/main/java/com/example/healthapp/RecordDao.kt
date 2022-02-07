package com.example.healthapp

import androidx.room.*
import com.example.healthapp.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(record : Record)

    @Update
    suspend fun updateRecord(record : Record)

    @Delete
    suspend fun deleteRecord(record : Record)

    // 큰 날짜부터 출력
    @Query("SELECT * FROM Record ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readAllData() : Flow<List<Record>>

    // 날짜 정보를 입력받아 그 날짜에 해당하는 메모만 반환
    @Query("SELECT * FROM Record WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun readDateData(year : Int, month : Int, day : Int) : List<Record>

    // 완료한 메모만 출력
    @Query("SELECT * FROM Record WHERE `check` = 1 ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readDoneData() : Flow<List<Record>>

}