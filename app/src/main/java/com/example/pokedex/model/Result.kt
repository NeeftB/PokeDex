package com.example.pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result (

     @SerializedName("results")
     @Expose
     var pokemons: List<PokemonReference>
)