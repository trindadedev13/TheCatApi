package dev.trindadedev.thecatapi.models

import kotlin.math.abs

data class Cat(
  val id: String,
  val url: String,
  val width: Int,
  val height: Float
) {
  val name: String 
    get ()
    {
      return when (abs(id.hashCode()) % 4)
      {
        0 -> "Mittens"
        1 -> "Felix"
        2 -> "Luna"
        else -> "Oliver"
      }
    }
}