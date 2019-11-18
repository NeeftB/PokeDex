package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.PokemonReference
import kotlinx.android.synthetic.main.result_item.view.*

class SearchAdapter(private val references: List<PokemonReference>, private val onClick: (PokemonReference) -> Unit)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.result_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return references.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(references[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(references[adapterPosition]) }
        }

        fun bind(pokemonReference: PokemonReference) {
            itemView.tvResultPokemonName.text = pokemonReference.pokeName.capitalize()
        }
    }
}
