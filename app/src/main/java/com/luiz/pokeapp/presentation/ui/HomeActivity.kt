package com.luiz.pokeapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.luiz.pokeapp.R
import com.luiz.pokeapp.data.remote.external.PokeApiRetrofit
import com.luiz.pokeapp.databinding.ActivityHomeBinding
import com.luiz.pokeapp.domain.model.PokemonApp
import com.luiz.pokeapp.presentation.ui.FormPokemon.Constants.EXTRA_POKEMON_ID
import com.luiz.pokeapp.presentation.ui.RecyclerView.CarouselAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val pokemonList = mutableListOf<PokemonApp>()

    private val pokeApi by lazy {
        PokeApiRetrofit.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //adapter
        val adapter = CarouselAdapter(pokemonList)

        lifecycleScope.launch {
            binding.progressbar.visibility = View.VISIBLE

            val response = pokeApi.getAllPokemon().body()?.results

            val pokemonListResponse = response?.map { result ->
                async {
                    val responsePokemon = pokeApi.getPokemon(result.name)
                    responsePokemon.body()?.let {
                        PokemonApp(
                            price = null,
                            nome = it.name.replaceFirstChar { it.uppercaseChar() },
                            uuid = null,
                            url = it.sprites.other.showdown.front_default
                        )
                    }
                }
            }?.awaitAll()?.filterNotNull()

            pokemonList.addAll(pokemonListResponse ?: emptyList())

            binding.progressbar.visibility = View.GONE
            adapter.notifyDataSetChanged()
        }

        enableEdgeToEdge()

        setContentView(binding.root)


        binding.homeCarouselRecyclerView.adapter = adapter

        //config carousel
        binding.homeCarouselRecyclerView.setHasFixedSize(true)
        val layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())

        binding.homeCarouselRecyclerView.layoutManager = layoutManager

        //rolagem do carousel
        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.homeCarouselRecyclerView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.homeButton.setOnClickListener()
        {
            val centerView = snapHelper.findSnapView(layoutManager)

            if (centerView != null) {
                val currentIndex = layoutManager?.getPosition(centerView)
                val currentPokemon = currentIndex?.let { pokemonList.get(it) }

                Log.i("TAG", "onCreate: $currentPokemon")

                val intent = Intent(this, FormPokemon::class.java)
                    .apply {
                        if (currentPokemon != null) {
                            putExtra(EXTRA_POKEMON_ID, currentPokemon)
                        }
                    }

                startActivity(intent)
            }
        }
    }
}