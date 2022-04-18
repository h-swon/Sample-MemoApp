package com.swon.sample_memoapp.data.dao

import androidx.room.*
import com.swon.sample_memoapp.data.entity.Memo

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memo: Memo)

    @Update
    suspend fun update(memo: Memo)

    @Delete
    suspend fun delete(memo: Memo)

    @Query("SELECT * FROM memo_table")
    suspend fun getAll(): List<Memo>

    @Query("SELECT * FROM memo_table ORDER BY mId DESC LIMIT 1")
    suspend fun getLastMemo(): Memo
}