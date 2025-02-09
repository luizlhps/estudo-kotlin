package com.luiz.pokeapp.data.remote.external

import com.luiz.pokeapp.data.remote.external.services.PokeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApiRetrofit {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val instance: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

}