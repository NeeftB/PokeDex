package com.example.pokedex.ui.add

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.ui.search.SearchActivityViewModel

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var addViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        addViewModel = ViewModelProviders.of(this).get(AddActivityViewModel::class.java)
        addViewModel.getPokemonByName(intent.getStringExtra(POKEMON_PAGE)!!)

        addViewModel.pokemon.observe(this, Observer { pokemon ->
            if (pokemon != null) {
                tvPokemonName.text = pokemon.name.capitalize()
                Glide.with(this).load(pokemon.getPokemonImage()).into(ivPokemonImage)
                tvWeight.text = getString(R.string.pokemon_weight, pokemon.weight)
                tvHeight.text = getString(R.string.pokemon_height, pokemon.height)
            }
        })

        addViewModel.error.observe(this, Observer {
            tvPokemonName.text = getString(R.string.pokemon_not_found)
            tvWeight.text = getString(R.string.pokemon_weight, "0")
            tvHeight.text = getString(R.string.pokemon_height, "0")
        })
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fab.setOnClickListener {
            if (addViewModel.pokemon.value != null) {
                addViewModel.addPokemon()
                Snackbar.make(
                    tvPokemonName, "${addViewModel.pokemon.value!!.name.capitalize()}" +
                            " is added to your Pokedex",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    tvPokemonName, "Cannot add Pokemon",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val POKEMON_PAGE = "Pokemon Page"
    }

}
