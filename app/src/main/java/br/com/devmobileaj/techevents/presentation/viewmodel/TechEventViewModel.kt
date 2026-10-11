package br.com.devmobileaj.techevents.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.devmobileaj.techevents.domain.TechEventRepository

class TechEventViewModel: ViewModel() {
    val repository = TechEventRepository()

    fun getEvents() = repository.getEvents()
}