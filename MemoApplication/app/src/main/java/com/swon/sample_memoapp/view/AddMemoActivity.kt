package com.swon.sample_memoapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.swon.sample_memoapp.R
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.data.type.Mode
import com.swon.sample_memoapp.databinding.ActivityAddMemoBinding
import com.swon.sample_memoapp.viewModel.AddViewModel

class AddMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMemoBinding
    private lateinit var addViewModel: AddViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_memo)
        addViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[AddViewModel::class.java]

        registerListeners()
        getData()
        bindData()
    }

    private fun getData() {
        val data = intent.getParcelableExtra<Memo>("memo")
        addViewModel.initData(data)
    }

    private fun bindData() {
        binding.addViewModel = addViewModel
        binding.lifecycleOwner = this
    }


    private fun registerListeners() {
        with(binding) {
            memoAddBackButton.setOnClickListener {
                onBackPressed()
            }

            memoAddButton.setOnClickListener {
                val memo = Memo(title = binding.memoTitleEdit.text.toString(), description = binding.memoDescEdit.text.toString())
                addViewModel?.completeMemo(memo)
            }

            memoDescEdit.addTextChangedListener {
                addViewModel?.setMode(Mode.UPDATE)
            }

            memoTitleEdit.addTextChangedListener {
                addViewModel?.setMode(Mode.UPDATE)
            }
        }
    }
}