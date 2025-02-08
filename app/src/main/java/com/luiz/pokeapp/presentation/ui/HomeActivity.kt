package com.luiz.pokeapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.carousel.HeroCarouselStrategy
import com.luiz.pokeapp.R
import com.luiz.pokeapp.databinding.ActivityHomeBinding
import com.luiz.pokeapp.domain.model.Pokemon
import com.luiz.pokeapp.presentation.ui.RecyclerView.CarouselAdapter
import java.io.Serializable
import java.math.BigDecimal

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContentView(binding.root)

        val pokemons = listOf(
            Pokemon(
                "1234",
                url = "https://static.wikia.nocookie.net/characterprofile/images/d/dd/Charizard%2C_the_Flame_Pokemon.png/revision/latest?cb=20170915013005",
                nome = "Charizard",
                price = BigDecimal(2000)
            ),

            Pokemon(
                "1234",
                url = "https://projectpokemon.org/home/uploads/monthly_2019_06/large.charizard-megax.png.1b778823c09285b7040924e22ccbc63a.png",
                nome = "Charizard Mega",
                price = BigDecimal(2000)
            )
        )

        //adapter
        val adapter = CarouselAdapter(pokemons)
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
                val currentPokemon = currentIndex?.let { pokemons.get(it) }

                Log.i("TAG", "onCreate: $currentPokemon")

                val intent = Intent(this, FormPokemon::class.java)
                    .apply {
                        if (currentPokemon != null) {
                            putExtra("pokemon", currentPokemon)
                        }
                    }

                startActivity(intent)
            }
        }
    }
}