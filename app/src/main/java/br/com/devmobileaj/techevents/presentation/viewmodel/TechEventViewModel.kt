package br.com.devmobileaj.techevents.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.devmobileaj.techevents.data.TechEventRepository
import br.com.devmobileaj.techevents.domain.ITechEventRepository

class TechEventViewModel(val repository: ITechEventRepository): ViewModel(){
       fun getEvents() = repository.getEventRepository()

       companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                   override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                        val repository = TechEventRepository()
                        return TechEventViewModel(repository) as T
            }
        }
    }
}