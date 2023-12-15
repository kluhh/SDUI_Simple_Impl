package com.example.sduisimpleimpl.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreensViewModel @Inject constructor(private val repository: Repository) : ViewModel()
{
    private val _sduiModel = MutableStateFlow<SDUIModel?>(null)
    val sduiModel: StateFlow<SDUIModel?> = _sduiModel.asStateFlow()



    init
    {
        fetchSDUIModel()
    }


    private fun fetchSDUIModel() = viewModelScope.launch {
        Log.d("API", "Fetching screen data")

        try
        {
            val result = repository.getData().collect { data ->
                _sduiModel.value = data
            }
            Log.d("viewmodel","$result")


        }
        catch (e: Exception)
        {
            Log.e("viewmodel", "Error fetching data", e)
        }
    }

}