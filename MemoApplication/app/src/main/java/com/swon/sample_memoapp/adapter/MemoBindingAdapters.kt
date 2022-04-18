package com.swon.sample_memoapp.adapter

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.data.type.Mode

@BindingAdapter("bind_memoList")
fun bindMemoList(recyclerView: RecyclerView, memoList: List<Memo>?) {
    memoList?.let {
        (recyclerView.adapter as MemoListAdapter).dataList = it
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("bind_mode")
fun bindMode(view: View, mode: Mode) {
    Log.e("MODE", mode.toString())
    when (mode) {
        Mode.UPDATE, Mode.ADD -> {
            view.visibility = View.VISIBLE
        }
        else -> {
            view.visibility = View.INVISIBLE
        }
    }
}




