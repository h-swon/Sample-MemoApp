package com.swon.sample_memoapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.data.type.Mode
import com.swon.sample_memoapp.repository.MemoRepository
import kotlinx.coroutines.launch
import java.util.logging.Logger

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val memoRepository: MemoRepository by lazy {
        MemoRepository(application)
    }

    val memo: LiveData<Memo>
        get() = _memo
    private val _memo: MutableLiveData<Memo> = MutableLiveData()

    val mode: LiveData<Mode>
        get() = _mode

    private val _mode: MutableLiveData<Mode> = MutableLiveData()

    fun initData(memo: Memo?) {
        _memo.value = memo
        if (memo == null) {
            _mode.value = Mode.ADD
        } else {
            setMode(Mode.READ)
        }
    }

    fun setMode(mode: Mode) {
        if (memo.value == null)
            return
        _mode.value = mode
    }

    fun completeMemo(tmpMemo: Memo) {
        when (mode.value) {
            Mode.ADD -> {
                addMemo(tmpMemo)
            }
            Mode.UPDATE -> {
                val updatedMemo = memo.value?.copy(title = tmpMemo.title, description = tmpMemo.description)
                updateMemo(updatedMemo!!)
                _memo.value = updatedMemo
            }
            else -> {}
        }
        _mode.value = Mode.READ
    }

    private fun getLastMemo() = viewModelScope.launch {
        _memo.value = memoRepository.getLastMemo()
    }

    private fun addMemo(memo: Memo) = viewModelScope.launch {
        memoRepository.addMemo(memo)
        getLastMemo()
    }

    private fun updateMemo(memo: Memo) = viewModelScope.launch {
        memoRepository.updateMemo(memo)
    }

    fun deleteMemo(memo: Memo) = viewModelScope.launch {
        memoRepository.deleteMemo(memo)
    }
}