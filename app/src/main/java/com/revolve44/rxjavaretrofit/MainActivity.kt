package com.revolve44.rxjavaretrofit

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.revolve44.rxjavaretrofit.network.ServiceBuilder
import com.revolve44.rxjavaretrofit.network.SuperResponse
import com.revolve44.rxjavaretrofit.util.api_key
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.http2.ErrorCode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apilaunch()
        Single.fromCallable {
            lol()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    private fun apilaunch(){
        val compositeDisposable = CompositeDisposable() //?
        compositeDisposable.add(
            ServiceBuilder.buildService().getWeather("55.751244","37.618423", api_key)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        response -> onResponse(response)

                }, {
                        t -> onFailure(t)
                    Log.e("ERROR", " ~ "+t)
                }))

    }

    private fun onResponse(response: SuperResponse) {

        //var a =  response

        Log.d("respone is ", " "+ Gson().toJson(response))
        Log.d("TAG", "onResponse: ConfigurationListener::"+response.id);
        Log.d("Threads num ", " respons is "+Thread.currentThread() + " UI is " + (Thread.currentThread() == Looper.getMainLooper().thread) )
        //Log.d("respone is ", " "+ respon)
    }

    private fun onFailure(t: Throwable) {
        //  HTTP 401 Unauthorized // retrofit2.adapter.rxjava2.HttpException: HTTP 401
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        Log.d("ERROR ", " "+t.message+" // " +t)

    }



    private fun lol(){
        var a = 0
        for (i in 1..100){
            Thread.sleep(1000)
            a++
            Log.d("OPPSy", "try = " + a  +" thread "+Thread.currentThread())
//            if (a<2){
//
//            }
        }
    }
}
