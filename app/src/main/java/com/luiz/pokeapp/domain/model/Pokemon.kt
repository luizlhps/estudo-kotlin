package com.luiz.pokeapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.util.UUID


@Entity
@Parcelize
data class Pokemon(
    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),

    val nome: String,

    val price: BigDecimal,

    val url: String

) : Parcelable



