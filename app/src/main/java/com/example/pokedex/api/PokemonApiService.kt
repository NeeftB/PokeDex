package com.example.pokedex.api

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("/api/v2/pokemon")
    fun getPokemonReferences(): Call<Result>

    @GET("/api/v2/pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>

}