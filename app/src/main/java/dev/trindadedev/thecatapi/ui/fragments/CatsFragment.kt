package dev.trindadedev.thecatapi.ui.fragments

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.trindadedev.thecatapi.R
import dev.trindadedev.thecatapi.ui.anims
import dev.trindadedev.thecatapi.adapters.CatsAdapter
import dev.trindadedev.thecatapi.databinding.FragmentCatsBinding
import dev.trindadedev.thecatapi.models.Cat
import dev.trindadedev.thecatapi.viewmodels.CatUiState
import dev.trindadedev.thecatapi.viewmodels.CatViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class CatsFragment () : Fragment ()
{
  private var _binding: FragmentCatsBinding? = null
  private val binding
    get () = checkNotNull (_binding) { "Cats Fragment have been destroyed" }

  private val viewModel: CatViewModel by viewModels ()
  private val catsAdapter by lazy {
    CatsAdapter ()
    { cat ->
      requireActivity().supportFragmentManager.commit ()
      {
        setReorderingAllowed (true)
        addToBackStack (null)
        anims ()
        replace (R.id.fragment, CatViewFragment (cat))
      }
    }
  }

  override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
  {
    _binding = FragmentCatsBinding.inflate (inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated (view: View, savedInstanceState: Bundle?)
  {
    binding.list.apply ()
    {
      layoutManager = LinearLayoutManager(requireActivity ())
      adapter = catsAdapter
    }
  }

  override fun onStart ()
  {
    super.onStart ()

    lifecycleScope.launch ()
    {
      viewModel.uiState.collectLatest ()
      { state ->
        when (state)
        {
          is CatUiState.Loading -> loading ()
          is CatUiState.Success -> success (state.cats)
          is CatUiState.Error -> error (state.message)
        }
      }
    }
  }

  fun loading ()
  {
    binding.loading.visibility = View.VISIBLE
  }

  fun success (cats: List <Cat>)
  {
    catsAdapter.submitList (cats)
    binding.loading.visibility = View.GONE
  }

  fun error (msg: String)
  {
    binding.loading.visibility = View.GONE
  }

  override fun onDestroyView ()
  {
    super.onDestroyView ()
    _binding = null
  }
}
