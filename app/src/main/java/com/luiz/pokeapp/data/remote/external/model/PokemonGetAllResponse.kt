package com.luiz.pokeapp.data.remote.external.model

import java.math.BigDecimal

data class PokemonGetAllResponse(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
) {
    data class Pokemon(
        val name: String,
        val url: String
    )
}

