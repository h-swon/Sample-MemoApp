package com.swon.sample_memoapp.repository

import android.app.Application
import android.util.Log
import com.swon.sample_memoapp.data.AppDatabase
import com.swon.sample_memoapp.data.entity.Memo

class MemoRepository(application: Application) {
    private val memoDao = AppDatabase.getInstance(application).memoDao()

    suspend fun addMemo(memo: Memo) {
        memoDao.insert(memo)
    }

    suspend fun updateMemo(memo: Memo) {
        memoDao.update(memo)
    }

    suspend fun deleteMemo(memo: Memo) {
        memoDao.delete(memo)
    }

    suspend fun getAll(): List<Memo> {
        return memoDao.getAll()
    }

    suspend fun getLastMemo(): Memo {
        return memoDao.getLastMemo()
    }

}