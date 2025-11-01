package dev.trindadedev.thecatapi.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import dev.trindadedev.thecatapi.R
import dev.trindadedev.thecatapi.databinding.ActivityMainBinding
import dev.trindadedev.thecatapi.ui.fragments.CatsFragment

public class MainActivity : AppCompatActivity ()
{
  private var _binding: ActivityMainBinding? = null
  
  private val binding: ActivityMainBinding
    get() = checkNotNull (_binding) { "Activity has been destroyed" }
  
  override fun onCreate (savedInstanceState: Bundle?)
  {
    super.onCreate (savedInstanceState)
    _binding = ActivityMainBinding.inflate (layoutInflater)
    setContentView (binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
      val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
      val navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

      view.setPadding(
        0,
        statusBars.top,
        0,
        navigationBars.bottom)
      insets
    }
    
    if (savedInstanceState == null)
    {
      supportFragmentManager.commit ()
      {
        setReorderingAllowed (true)
        add (binding.fragment.id, CatsFragment ())
      }
    }
  }
  
  override fun onDestroy ()
  {
    super.onDestroy ()
    _binding = null
  }
}
