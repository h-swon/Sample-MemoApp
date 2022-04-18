package com.swon.sample_memoapp.view

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.swon.sample_memoapp.R
import com.swon.sample_memoapp.adapter.MemoListAdapter
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.databinding.ActivityMainBinding
import com.swon.sample_memoapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    private var addButtonClicked = false
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[MainViewModel::class.java]

        bindData()
        setAdapter()
        registerListeners()
    }

    override fun onRestart() {
        super.onRestart()
        mainViewModel.getAll()

    }

    private fun bindData() {
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun setAdapter() {
        val memoAdapter = MemoListAdapter(this, object : MemoListAdapter.OnItemClickListener {
            override fun onItemClick(v: View, memo: Memo) {
                val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
                intent.putExtra("memo", memo)
                startActivity(intent)
            }
        }, object : MemoListAdapter.OnItemLongClickListener {
            override fun onItemLongClick(v: View, memo: Memo) {
                // todo 메뉴
            }
        })
        binding.memoListRecyclerView.adapter = memoAdapter
    }
    // todo 람다로 전환 왜 안됑

    private fun registerListeners() {
        with(binding) {
            addFloatingButton.setOnClickListener {
                onAddButtonClicked()
            }

            addMemoButton.setOnClickListener {
                val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
                startActivity(intent)
            }

            addTodoButton.setOnClickListener {

            }
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(addButtonClicked)
        setAnimation(addButtonClicked)
        setColor(addButtonClicked)
        addButtonClicked = !addButtonClicked
    }

    private fun setColor(clicked: Boolean) {
        if (!clicked) {
            binding.addFloatingButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.close_button_color))
        } else {
            binding.addFloatingButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.add_button_color))
        }
    }

    private fun setVisibility(clicked: Boolean) {
        with(binding) {
            addMemoButton.isVisible = !clicked
            addTodoButton.isVisible = !clicked
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            with(binding) {
                addFloatingButton.startAnimation(rotateOpen)
                addMemoButton.startAnimation(fromBottom)
                addTodoButton.startAnimation(fromBottom)
            }
        } else {
            with(binding) {
                addFloatingButton.startAnimation(rotateClose)
                addMemoButton.startAnimation(toBottom)
                addTodoButton.startAnimation(toBottom)
            }
        }
    }
}