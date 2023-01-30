package com.ali.grandtask.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


data class RedditResponse(
    val data: RedditData? = null
)

data class RedditData(
    val children: List<RedditItem>? = null
)

@Entity
@Parcelize
data class RedditItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val data: RedditItemData? = null,
): Parcelable


@Parcelize
class RedditItemData(
    val title: String? = null,
    val thumbnail: String? = null,
): Parcelable
