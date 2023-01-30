package com.ali.grandtask.data.remote

import com.ali.grandtask.domain.models.RedditResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ali.grandtask.utils.Constants
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// Since we only have one service, this can all go in one file.
// If you add more services, split this to multiple files and make sure to share the retrofit
// object between services.

/**
 * A retrofit service to fetch a reddit news list.
 */
interface RedditNewsApiService {
    @GET(".json")
    suspend fun getRedditNews(): RedditResponse
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/**
 * client to set request timout
 * */
val client =  OkHttpClient().newBuilder().apply {
     this.connectTimeout(10, TimeUnit.SECONDS)
    this.readTimeout(30, TimeUnit.SECONDS)
}

/**
 * Main entry point for network access. Call like `Network.redditNewsService.getRedditNews()`
 */
object Network {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val redditNewsService = retrofit.create(RedditNewsApiService::class.java)
}