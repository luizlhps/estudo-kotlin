package com.luiz.pokeapp.presentation.ui.RecyclerView

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.ImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.load
import coil3.request.crossfade
import com.luiz.pokeapp.databinding.ActivityItemRegistradosBinding
import com.luiz.pokeapp.databinding.ActivityListaRegistradosBinding
import com.luiz.pokeapp.domain.model.PokemonApp
import java.text.NumberFormat
import java.util.Locale

public class ListaRegistradosAdapter(
    private val pokemons: List<PokemonApp>,
    var quandoClica: (pokemon: PokemonApp ) -> Unit = {}
) :
    RecyclerView.Adapter<ListaRegistradosAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ActivityItemRegistradosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var pokemon: PokemonApp

        fun bind(pokemon: PokemonApp) {
            this.pokemon = pokemon


            binding.listaItemRegistradosImageView.load(pokemon.url) {
                crossfade(true)
                listener(
                    onStart = {
                        binding.listaItemRegistradosProgressbar.visibility = View.VISIBLE
                    },
                    onSuccess = { _, _ ->
                        binding.listaItemRegistradosProgressbar.visibility = View.GONE
                    },
                    onCancel = {
                        binding.listaItemRegistradosProgressbar.visibility = View.GONE
                    },
                    onError = { _, _ ->
                        binding.listaItemRegistradosProgressbar.visibility = View.GONE
                    }
                )
            }
            binding.listaItemRegistradosNome.text = pokemon.nome
            val currencyInstance: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            binding.listaItemRegistradosValor.text = currencyInstance.format(pokemon.price)

            itemView.setOnClickListener()
            {
                val pokemon = PokemonApp(
                    nome = pokemon.nome,
                    uuid = pokemon.uuid,
                    url = pokemon.url,
                    price = pokemon.price,
                )

                quandoClica(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaRegistradosAdapter.ViewHolder {
        return ViewHolder(
            ActivityItemRegistradosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }



}


