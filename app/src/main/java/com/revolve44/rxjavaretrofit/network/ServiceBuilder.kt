package com.revolve44.rxjavaretrofit.network

import android.os.Looper
import android.util.Log
import com.odhiambopaul.movies.network.TmdbEndpoints

import com.revolve44.rxjavaretrofit.util.base_url
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Thread.currentThread

object ServiceBuilder {
    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // rx
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TmdbEndpoints::class.java) //rx

    fun buildService(): TmdbEndpoints { //rx
        Log.d("Threads num ", " SB is "+currentThread().name + "// UI is " + (Thread.currentThread() == Looper.getMainLooper().thread) )
        return retrofit //rx
    }
}
