package com.luiz.pokeapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.luiz.pokeapp.data.local.dao.PokemonDao
import com.luiz.pokeapp.domain.model.Pokemon

import androidx.room.Room
import androidx.room.TypeConverters
import com.luiz.pokeapp.data.local.converter.BigDecimalConverter

@Database(entities = [Pokemon::class], version = 1, exportSchema = false )
@TypeConverters(BigDecimalConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (db == null) {
                synchronized(this) {
                    if (db == null) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "poke_app"
                        ).build()
                        db = instance
                    }
                }
            }
            return db as AppDatabase
        }
    }
}