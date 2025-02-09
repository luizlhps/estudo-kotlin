package com.luiz.pokeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class PokemonApp(
    val uuid: String?,
    val nome: String?,
    val price: BigDecimal?,
    val url: String?
) : Parcelable



