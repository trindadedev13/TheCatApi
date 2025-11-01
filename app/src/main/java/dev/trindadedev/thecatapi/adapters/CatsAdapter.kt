package dev.trindadedev.thecatapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dev.trindadedev.thecatapi.databinding.LayoutCatBinding
import dev.trindadedev.thecatapi.models.Cat
import dev.trindadedev.thecatapi.ui.getShapeForListItem

class CatsAdapter (private val onCatClick: (Cat) -> Unit): ListAdapter <Cat, CatsAdapter.ViewHolder> (CatsDiffUtil ())
{
  override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder
  {
    val binding = LayoutCatBinding.inflate (LayoutInflater.from (parent.context), parent, false)
    return ViewHolder (binding)
  }

  override fun onBindViewHolder (holder: ViewHolder, pos: Int)
  {
    holder.bind (pos)
  }

  inner class ViewHolder (val binding: LayoutCatBinding): RecyclerView.ViewHolder (binding.root)
  {
    fun bind (pos: Int)
    {
      val cat = getItem (pos)

      binding.name.text = cat.name
      binding.id.text = "ID: ${cat.id}"

      binding.img.load (cat.url)
      {
        crossfade (true)
        transformations (CircleCropTransformation ())
      }

      binding.root.apply ()
      {
        setOnClickListener ()
        { v ->
          onCatClick (cat)
        }
        setBackgroundResource (getShapeForListItem (currentList, pos))
      }
    }
  }

  class CatsDiffUtil: DiffUtil.ItemCallback <Cat> ()
  {
    override fun areItemsTheSame (item: Cat, other: Cat) = item == other
    override fun areContentsTheSame (item: Cat, other: Cat) = item.id == other.id
  }
}