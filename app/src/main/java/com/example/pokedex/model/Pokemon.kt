package com.example.pokedex.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "pokemon_table")
@Parcelize
data class Pokemon (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    @Expose(serialize = false, deserialize = false)
    val rowId: Long? = null,

    @SerializedName("id")
    @ColumnInfo(name = "pokemonId")
    val pokemonId: Long,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name ="weight")
    @SerializedName("weight")
    val weight: String,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: String

) : Parcelable {
    fun getPokemonImage() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"

}