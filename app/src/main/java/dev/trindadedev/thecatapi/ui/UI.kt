package dev.trindadedev.thecatapi.ui

import androidx.annotation.DrawableRes
import androidx.fragment.app.FragmentTransaction
import dev.trindadedev.thecatapi.R

fun FragmentTransaction.anims ()
{
  setCustomAnimations (
    android.R.anim.fade_in,
    android.R.anim.fade_out,
    android.R.anim.fade_in,
    android.R.anim.fade_out
  )
}

@DrawableRes
fun <T> getShapeForListItem (list: List <T>, pos: Int): Int
{
  return when {
    list.size == 1 -> R.drawable.shape_alone
    pos == 0 -> R.drawable.shape_top
    pos == list.size - 1 -> R.drawable.shape_bottom
    else -> R.drawable.shape_middle
  }
}