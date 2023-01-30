package com.ali.grandtask.data.work


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ali.grandtask.data.local.getDatabase
import com.ali.grandtask.data.repo.MainRepo
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MainRepo(database)
        return try {
            repository.refreshRedditNews()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}