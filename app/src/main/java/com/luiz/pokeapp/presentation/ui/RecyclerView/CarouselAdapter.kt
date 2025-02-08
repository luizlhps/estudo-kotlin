package com.luiz.pokeapp.presentation.ui.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.luiz.pokeapp.databinding.ActivityItemCarouselBinding
import com.luiz.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.coroutineScope

class CarouselAdapter(private val pokemons: List<Pokemon>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ActivityItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var pokemon: Pokemon

        init {
            itemView.setOnClickListener {
                Log.i("TAG", "asddf:${pokemon} ")
            }

            if(::pokemon.isInitialized)
            {

            }
        }

        fun bind(pokemon: Pokemon) {
            this.pokemon = pokemon

            binding.carouselImageView.load(pokemon.url) {
                crossfade(true)
                listener(
                    onStart = {
                        binding.carouselProgressbar.visibility = View.VISIBLE
                    },
                    onSuccess = { _, _ ->
                        binding.carouselProgressbar.visibility = View.GONE
                    },
                    onCancel = {
                        binding.carouselProgressbar.visibility = View.GONE
                    },
                    onError = {
                            _, _ ->
                        binding.carouselProgressbar.visibility = View.GONE
                    }
                )
            }
            binding.carouselName.text = pokemon.nome
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselAdapter.ViewHolder {
        return ViewHolder(
            ActivityItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarouselAdapter.ViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


}