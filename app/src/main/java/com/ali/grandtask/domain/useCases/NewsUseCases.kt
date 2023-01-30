package com.ali.grandtask.domain.useCases

import androidx.lifecycle.LiveData
import com.ali.grandtask.data.repo.MainRepo
import com.ali.grandtask.domain.models.RedditItem

class NewsUseCases constructor(
    val repository: MainRepo
) {

    operator fun invoke(): LiveData<List<RedditItem>> {
        return repository.redditNews
    }
}