package com.swon.sample_memoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swon.sample_memoapp.data.entity.Memo
import com.swon.sample_memoapp.databinding.ItemMemoBinding

class MemoListAdapter(
    private val context: Context,
    private val clickListener: OnItemClickListener,
    private val longClickListener: OnItemLongClickListener
) : RecyclerView.Adapter<MemoListAdapter.MemoViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(v: View, memo: Memo)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, memo: Memo)
    }

    var dataList = listOf<Memo>()

    inner class MemoViewHolder(private val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.memo = memo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(context), parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(it, dataList[position])
        }
        holder.itemView.setOnLongClickListener {
            longClickListener.onItemLongClick(it, dataList[position])
            true
        }
    }

    override fun getItemCount() = dataList.size
}