package com.example.pokedex.repository

import com.example.pokedex.api.PokemonApi
import com.example.pokedex.api.PokemonApiService

class PokemonApiRepository {
    private val pokemonApi: PokemonApiService = PokemonApi.createApi()


    fun getAllPokemonReferences() = pokemonApi.getPokemonReferences()

    fun getPokemonByName(name: String) = pokemonApi.getPokemon(name)

}