package com.ali.grandtask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ali.grandtask.domain.models.RedditItem
import com.ali.grandtask.domain.models.RedditItemData
import com.ali.grandtask.utils.Converter

@Database(entities = [RedditItem::class], version = 1)
@TypeConverters(Converter::class)
abstract class RedditNewsDatabase : RoomDatabase() {

    abstract val redditNewsDao: RedditNewsDao
}

private lateinit var INSTANCE: RedditNewsDatabase


fun getDatabase(context: Context): RedditNewsDatabase {
    val converter=Converter()
    synchronized(RedditNewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                RedditNewsDatabase::class.java,
                "RedditDB")
                .addTypeConverter(converter)
                .build()
        }
    }
    return INSTANCE
}