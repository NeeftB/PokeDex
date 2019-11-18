package com.example.pokedex.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val pokemonRepository = PokemonRepository(application.applicationContext)

    val pokemons = pokemonRepository.getAllPokemons()

    fun deleteAllPokemons() {
        ioScope.launch { pokemonRepository.deleteAllPokemons() }
    }

    fun deletePokemon(pokemon: Pokemon) {
        ioScope.launch { pokemonRepository.deletePokemon(pokemon) }
    }

}