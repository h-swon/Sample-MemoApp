package com.swon.sample_memoapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.repository.MemoRepository
import kotlinx.coroutines.launch
import java.util.logging.Logger

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val memoRepository: MemoRepository by lazy {
        MemoRepository(application)
    }

    val memoList: LiveData<List<Memo>>
        get() = _memoList
    private val _memoList: MutableLiveData<List<Memo>> = MutableLiveData()

    init{
        getAll()
    }

    fun getAll() = viewModelScope.launch {
        _memoList.value = memoRepository.getAll()
    }

    fun deleteMemo(memo: Memo) = viewModelScope.launch {
        memoRepository.deleteMemo(memo)
        getAll()
    }
}