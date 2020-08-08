package com.revolve44.rxjavaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.revolve44.rxjavaretrofit.network.PopularMovies
import com.revolve44.rxjavaretrofit.network.ServiceBuilder
import com.revolve44.rxjavaretrofit.util.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.revolve44.rxjavaretrofit.network.Result

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compositeDisposable = CompositeDisposable() //?
        compositeDisposable.add(
                ServiceBuilder.buildService().getMovies(api_key)

                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({response ->

                            onResponse(response)

                        }, {
                            t -> onFailure(t)
                        }))


    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        Log.d("ERROR ", " "+t.message)
    }

    private fun onResponse(response: PopularMovies) {

        Log.d("respone is ", " "+response)
        Log.d("Threads num ", " respons is "+Thread.currentThread() + " UI is " + (Thread.currentThread() == Looper.getMainLooper().thread) )

    }

    private fun lol(){
        var movie: Result

        //Log.d(" movie is ", " $movie")
    }
}
