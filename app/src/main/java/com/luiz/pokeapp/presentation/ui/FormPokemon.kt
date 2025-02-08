package com.luiz.pokeapp.presentation.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luiz.pokeapp.R
import com.luiz.pokeapp.databinding.ActivityFormPokemonBinding
import com.luiz.pokeapp.databinding.ActivityHomeBinding
import com.luiz.pokeapp.domain.model.Pokemon
import java.math.BigDecimal

class FormPokemon : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormPokemonBinding.inflate(layoutInflater)
    }

    private val pokemonGetExtra by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("CHAVE_PRODUTO",Pokemon::class.java)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Pokemon>("CHAVE_PRODUTO")
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

        salvar()

    }

    private fun salvar() {
        binding.btnSalvar.setOnClickListener() {
            val valueString = binding.valor.text.toString()
            val value = if(valueString.isBlank()) BigDecimal.ZERO else BigDecimal(valueString)


            //SALVA NO BANCO
            Log.i("TAG", "salvar: $value  A ${pokemonGetExtra?.nome}")

            //VAI PARA TELA DE TODOS OS POKEMONS REGISTRADOS
        }
    }
}