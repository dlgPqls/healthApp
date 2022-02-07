package com.example.healthapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthapp.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class RecordDatabase : RoomDatabase(){

    abstract fun recordDao() : RecordDao

    companion object{
        @Volatile
        private var instance : RecordDatabase? = null

        fun getDatabase(context : Context) : RecordDatabase? {
            if(instance == null){
                synchronized(RecordDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecordDatabase::class.java,
                        "record_database"
                    ).build()
                }
            }
            return instance
        }
    }
}
