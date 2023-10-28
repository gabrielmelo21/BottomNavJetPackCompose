package com.melo.bottomnavjetpackcompose.api.dataClasses

data class Calorias(
    val id: Int,
    val calorias_atual: Int,
    val tmb: Int,
    val deficit_calorico: Int?,
    val data_dia: String?
)