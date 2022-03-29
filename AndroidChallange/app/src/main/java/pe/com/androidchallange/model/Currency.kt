package pe.com.androidchallange.model

import java.io.Serializable

data class Currency(
    val name: String, val valueToOneEuro: Double, val flag: Int, val symbol: String
):Serializable
