package com.swon.sample_memoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swon.sample_memoapp.data.dao.MemoDao
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.utilities.DATABASE_NAME

@Database(entities = [Memo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}