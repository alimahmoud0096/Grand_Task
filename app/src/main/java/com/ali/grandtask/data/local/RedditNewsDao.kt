package com.ali.grandtask.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ali.grandtask.domain.models.RedditItem

@Dao
interface RedditNewsDao {

    @Query("select * from RedditItem")
    fun getRedditNews(): LiveData<List<RedditItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRedditItem(asteroids: List<RedditItem>)

    @Query("delete from RedditItem")
    fun deleteAllRedditItem()

}