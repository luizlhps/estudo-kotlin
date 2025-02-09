package com.luiz.pokeapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.luiz.pokeapp.R
import com.luiz.pokeapp.databinding.ActivityListaRegistradosBinding
import com.luiz.pokeapp.domain.model.PokemonApp
import com.luiz.pokeapp.presentation.ui.FormPokemon.Constants.EXTRA_POKEMON_ID
import com.luiz.pokeapp.presentation.ui.RecyclerView.ListaRegistradosAdapter
import java.math.BigDecimal

class ListaRegistradosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaRegistradosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.listaRegistradosFloatingActionButton.setOnClickListener()
        {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val pokemons = listOf(
            PokemonApp(
                "1",
                url = "https://static.wikia.nocookie.net/characterprofile/images/d/dd/Charizard%2C_the_Flame_Pokemon.png/revision/latest?cb=20170915013005",
                nome = "Charizard",
                price = BigDecimal(2000)
            ),

            PokemonApp(
                "2",
                url = "https://projectpokemon.org/home/uploads/monthly_2019_06/large.charizard-megax.png.1b778823c09285b7040924e22ccbc63a.png",
                nome = "Charizard Mega",
                price = BigDecimal(2000)
            )
        )
        val adapter = ListaRegistradosAdapter(pokemons)
        binding.recyclerView.adapter = adapter
        adapter.quandoClica = { currentPokemon ->
            val intent = Intent(this, FormPokemon::class.java)
                .apply {
                    putExtra(EXTRA_POKEMON_ID, currentPokemon)
                }

            startActivity(intent)
        }

        Log.i("ListaRegistradosActivity", "Total de Pokémons: ${pokemons.size}")
    }
}