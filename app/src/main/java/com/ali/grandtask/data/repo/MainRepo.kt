package com.ali.grandtask.data.repo

import androidx.lifecycle.LiveData
import com.ali.grandtask.data.local.RedditNewsDatabase
import com.ali.grandtask.data.remote.Network
import com.ali.grandtask.domain.models.RedditItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainRepo(val database: RedditNewsDatabase) {

    /**
     * A list of Reddit news.
     */
    val redditNews: LiveData<List<RedditItem>> =database.redditNewsDao.getRedditNews()

    /**
     * Refresh the redditNews stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the redditNews for use, observe [redditNews]
     */
    suspend fun refreshRedditNews() {
        withContext(Dispatchers.IO) {
            runBlocking {
                database.redditNewsDao.deleteAllRedditItem()
                val redditNews = Network.redditNewsService.getRedditNews()
                redditNews.data?.children?.let { insertAllRedditNews(it) }
            }
        }
    }

    private  fun insertAllRedditNews(redditNews: List<RedditItem>) {
        database.redditNewsDao.insertAllRedditItem(redditNews)
    }
}