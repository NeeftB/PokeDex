package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonReference(

    @SerializedName("name")
    var pokeName: String,

    @SerializedName("url")
    var pokemonUrl: String
)