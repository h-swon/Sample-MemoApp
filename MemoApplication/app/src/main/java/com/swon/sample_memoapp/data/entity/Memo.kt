package com.swon.sample_memoapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    val mId: Int = 0,
    val title: String? = "ë´ėŠėė",
    val description: String?
) : Parcelable