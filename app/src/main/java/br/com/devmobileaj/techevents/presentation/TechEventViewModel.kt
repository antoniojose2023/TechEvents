package br.com.devmobileaj.techevents.presentation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devmobileaj.techevents.domain.TechEventRepository
import kotlinx.coroutines.launch

class TechEventViewModel: ViewModel() {
    val repository = TechEventRepository()

    fun getEvents() = repository.getEvents()
}