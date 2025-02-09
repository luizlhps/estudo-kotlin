package com.luiz.pokeapp.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import coil3.request.crossfade
import com.luiz.pokeapp.R
import com.luiz.pokeapp.databinding.ActivityFormPokemonBinding
import com.luiz.pokeapp.databinding.ActivityHomeBinding
import com.luiz.pokeapp.domain.model.Pokemon
import com.luiz.pokeapp.domain.model.PokemonApp
import com.luiz.pokeapp.presentation.ui.FormPokemon.Constants.EXTRA_POKEMON_ID
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class FormPokemon(
) : AppCompatActivity() {
    object Constants {
        const val EXTRA_POKEMON_ID = "extra_pokemon_id"
    }


    private val binding by lazy {
        ActivityFormPokemonBinding.inflate(layoutInflater)
    }

    private val pokemonGetExtra by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_POKEMON_ID, PokemonApp::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<PokemonApp>(EXTRA_POKEMON_ID)
        }
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

        binding.imageView.load(pokemonGetExtra?.url) {
            crossfade(true)
            listener(
                onStart = {
                    binding.progressbar.visibility = View.VISIBLE
                },
                onSuccess = { _, _ ->
                    binding.progressbar.visibility = View.GONE
                },
                onCancel = {
                    binding.progressbar.visibility = View.GONE
                },
                onError = { _, _ ->
                    binding.progressbar.visibility = View.GONE
                }
            )
        }
        binding.textView.text = pokemonGetExtra?.nome

        if (pokemonGetExtra?.price != null) {
            binding.valor.setText(pokemonGetExtra!!.price!!.toPlainString())

        }

        salvar()
    }

    private fun salvar() {


        binding.btnSalvar.setOnClickListener() {
            val valueString = binding.valor.text.toString()
            val value = if (valueString.isBlank()) BigDecimal.ZERO else BigDecimal(valueString)


            //SALVA NO BANCO
            Log.i("TAG", "salvar: $value  A ${pokemonGetExtra?.nome}")

            val pokemon = pokemonGetExtra?.let { pokemon ->
                PokemonApp(
                    nome = pokemon.nome,
                    uuid = pokemon.uuid,
                    price = value,
                    url = pokemon.url
                )
            }

            //VAI PARA TELA DE TODOS OS POKEMONS REGISTRADOS
            val intent = Intent(this, ListaRegistradosActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}