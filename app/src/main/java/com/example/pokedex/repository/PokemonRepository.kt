package com.example.pokedex.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pokedex.api.PokemonApi
import com.example.pokedex.api.PokemonApiService
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.database.PokemonRoomDatabase
import com.example.pokedex.model.Pokemon

class PokemonRepository(context: Context) {
    private val pokemonDao: PokemonDao

    init {
        val database = PokemonRoomDatabase.getDatabase(context)
        pokemonDao = database!!.pokemonDao()
    }

    fun getAllPokemons(): LiveData<List<Pokemon>> {
        return pokemonDao.getAllPokemons()
    }

    suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemon(pokemon: Pokemon) {
        pokemonDao.deletePokemon(pokemon)
    }

    suspend fun deleteAllPokemons() {
        pokemonDao.deleteAllPokemons()
    }

}