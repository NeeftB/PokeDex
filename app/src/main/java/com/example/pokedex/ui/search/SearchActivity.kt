package com.example.pokedex.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.adapter.SearchAdapter
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonReference
import com.example.pokedex.ui.add.AddActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.pokemon_item.*

class SearchActivity : AppCompatActivity() {

    private val references = arrayListOf<PokemonReference>()
    private val searchAdapter = SearchAdapter(references) { pokemon ->
        onReferenceClick(pokemon)
    }
    private lateinit var viewModel: SearchActivityViewModel


    private fun onReferenceClick(pokemonReference: PokemonReference) {
        val pokemonActivityIntent = Intent(this, AddActivity::class.java)
        pokemonActivityIntent.putExtra(AddActivity.POKEMON_PAGE, pokemonReference.pokeName)
        startActivity(pokemonActivityIntent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initViews()
        initViewModel()

    }

    private fun searchPokemon() {
        val pokemonActivityIntent = Intent(this, AddActivity::class.java)
        if(isSearchValid()) {
            pokemonActivityIntent.putExtra(AddActivity.POKEMON_PAGE, etSearch.text.toString()
                .toLowerCase())
            startActivity(pokemonActivityIntent)
        }

    }

    private fun isSearchValid(): Boolean {
        return if(etSearch.text!!.isNotBlank()){
            true
        } else {
            Toast.makeText(this, "Please fill in a Pokemon name", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel::class.java)
        viewModel.getPokemonReferences()
        viewModel.pokemonReferences.observe(this, Observer {
            references.clear()
            references.addAll(it)
            searchAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvPokemonList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvPokemonList.adapter = searchAdapter


        btnSearch.setOnClickListener { searchPokemon() }
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
}
