package com.example.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonRoomDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao


    companion object {
        private const val DATABASE_NAME = "POKEMON_DATABASE"

        @Volatile
        private var pokemonRoomDatabaseInstance:
                PokemonRoomDatabase? = null

        fun getDatabase(context: Context): PokemonRoomDatabase? {
            if(pokemonRoomDatabaseInstance == null) {
                synchronized(PokemonRoomDatabase::class.java){
                    if (pokemonRoomDatabaseInstance == null) {
                        pokemonRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,
                                PokemonRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return pokemonRoomDatabaseInstance
        }

    }
}