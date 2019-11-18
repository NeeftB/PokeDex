package com.example.pokedex.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonReference
import com.example.pokedex.model.Result
import com.example.pokedex.repository.PokemonApiRepository
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val pokemonApiRepository = PokemonApiRepository()
    val pokemonReferences = MutableLiveData<List<PokemonReference>>()
    val error = MutableLiveData<String>()

    fun getPokemonReferences(){
        pokemonApiRepository.getAllPokemonReferences().enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.isSuccessful) pokemonReferences.value = response.body()!!.pokemons
                else error.value = "An error occurred ${response.errorBody().toString()}"
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}