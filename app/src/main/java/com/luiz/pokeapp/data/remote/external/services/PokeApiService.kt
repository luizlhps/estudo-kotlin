package com.luiz.pokeapp.data.remote.external.services

import com.luiz.pokeapp.data.remote.external.model.PokemonGetAllResponse
import com.luiz.pokeapp.data.remote.external.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<PokemonResponse>

    @GET("pokemon/")
    suspend fun getAllPokemon(): Response<PokemonGetAllResponse>
}