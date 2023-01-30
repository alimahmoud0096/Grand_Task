package com.ali.grandtask.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.ali.grandtask.domain.models.RedditItem
import com.ali.grandtask.presentation.ui.main.adapter.NewsAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, image:String) {
    if(image.isNullOrEmpty()){
        imageView.visibility=View.GONE
        return
    }
    Picasso.get().load(image).into(imageView)
}

@BindingAdapter("bindRedditNewsData")
fun bindRedditNewsData(view: RecyclerView, news: LiveData<List<RedditItem>>) {
    (view.adapter as NewsAdapter).submitList(news.value)
}
