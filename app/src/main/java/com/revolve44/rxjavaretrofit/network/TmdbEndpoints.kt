package com.odhiambopaul.movies.network

import com.revolve44.rxjavaretrofit.network.PopularMovies
import com.revolve44.rxjavaretrofit.network.SuperResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22

interface TmdbEndpoints {

    @GET("data/2.5/weather?")
    fun getWeather(@Query("lat") latitude: String,
                   @Query("lon") longitude: String,
                   @Query("APPID") app_id: String): Observable<SuperResponse>

//    @GET("/3/movie/popular")
//    fun getMovies(@Query("api_key") key: String): Call<PopularMovies>
    //https://api.openweathermap.org/data/2.5/weather?lat

}