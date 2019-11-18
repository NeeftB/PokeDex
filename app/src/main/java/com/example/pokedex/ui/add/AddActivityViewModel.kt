package com.example.pokedex.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonApiRepository
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val pokemonRepository = PokemonRepository(application.applicationContext)
    private val pokemonApiRepository = PokemonApiRepository()
    val pokemon = MutableLiveData<Pokemon>()
    private val mainScope = CoroutineScope(Dispatchers.Main)
    val error = MutableLiveData<String>()

    fun addPokemon() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                pokemonRepository.insertPokemon(pokemon.value!!)
            }
        }
    }

    fun getPokemonByName(name: String) {
        pokemonApiRepository.getPokemonByName(name).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) pokemon.value = response.body()!!
                else error.value = "An error occurred ${response.errorBody().toString()}"
            }


            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                error.value = t.message
            }
        })
    }


}