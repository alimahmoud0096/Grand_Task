package com.ali.grandtask.presentation.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.ali.grandtask.data.local.getDatabase
import com.ali.grandtask.data.repo.MainRepo
import com.ali.grandtask.presentation.ui.main.adapter.NewsAdapter
import com.ali.grandtask.presentation.ui.main.adapter.RedditItemClickListener
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MainViewModel(application: Application, val redditItemClickListener: RedditItemClickListener) :
    AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val mainRepo = MainRepo(database)
    var newsAdapter = NewsAdapter(redditItemClickListener)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            try {
                mainRepo.refreshRedditNews()
            }catch (e:HttpException){}
            catch (e:Exception){}

        }
    }

    var redditNews = mainRepo.redditNews


/**
 * Factory for constructing MainViewModel with parameter
 */
class Factory(val app: Application, val redditItemClickListener: RedditItemClickListener) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(app, redditItemClickListener) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
}