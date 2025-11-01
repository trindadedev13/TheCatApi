package dev.trindadedev.thecatapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.trindadedev.thecatapi.models.Cat
import dev.trindadedev.thecatapi.services.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CatUiState
{
  object Loading : CatUiState ()
  data class Success (val cats: List <Cat>) : CatUiState ()
  data class Error (val message: String) : CatUiState ()
}

class CatViewModel : ViewModel ()
{
  private val _uiState = MutableStateFlow <CatUiState> (CatUiState.Loading)
  val uiState: StateFlow <CatUiState> = _uiState

  init
  {
    fetchCats ()
  }

  fun fetchCats (limit: Int = 10)
  {
    viewModelScope.launch ()
    {
      _uiState.value = CatUiState.Loading
      try
      {
        val cats = RetrofitInstance.api.getCats (limit)
         _uiState.value = CatUiState.Success (cats)
      } catch (e: Exception)
      {
        _uiState.value = CatUiState.Error (e.localizedMessage ?: "unknow err")
      }
    }
  }
}