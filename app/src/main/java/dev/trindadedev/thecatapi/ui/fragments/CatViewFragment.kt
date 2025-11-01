package dev.trindadedev.thecatapi.ui.fragments

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import dev.trindadedev.thecatapi.R
import dev.trindadedev.thecatapi.adapters.CatsAdapter
import dev.trindadedev.thecatapi.databinding.FragmentCatViewBinding
import dev.trindadedev.thecatapi.models.Cat

class CatViewFragment (private val cat: Cat) : Fragment ()
{
  private var _binding: FragmentCatViewBinding? = null
  private val binding
    get () = checkNotNull (_binding) { "Cats Fragment have been destroyed" }

  override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
  {
    _binding = FragmentCatViewBinding.inflate (inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated (view: View, savedInstanceState: Bundle?)
  {
    binding.cImage.load (cat.url)
    {
      crossfade (true)
      transformations (CircleCropTransformation ())
    }

    binding.cName.text = cat.name
    binding.cId.text = "ID: ${cat.id}"
  }

  override fun onDestroyView ()
  {
    super.onDestroyView ()
    _binding = null
  }
}
