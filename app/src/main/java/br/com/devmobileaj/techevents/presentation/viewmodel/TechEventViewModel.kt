package br.com.devmobileaj.techevents.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.devmobileaj.techevents.data.TechEventRepository
import br.com.devmobileaj.techevents.domain.Evento
import br.com.devmobileaj.techevents.domain.ITechEventRepository
import br.com.devmobileaj.techevents.util.States
import kotlinx.coroutines.launch

class TechEventViewModel(val repository: ITechEventRepository): ViewModel(){

       val eventos = mutableListOf<Evento>()
       private val _states = MutableLiveData<States>()
       val states: LiveData<States> = _states

        var busca = ""
        var categoria = ""
        var tipo = ""

        init {
            getEvents()
        }

        private fun getEvents(){

            _states.value = States.Loading

            viewModelScope.launch {
                 try {
                     val lista =  repository.getEventRepository()
                     if(lista.isNotEmpty()){
                         eventos.addAll( lista )
                         filtroListaEventos()
                     }else{
                         _states.value = States.isEmptyListEevent
                     }
                 }catch (ex: Exception){
                         _states.value = States.OnError("Erro ao buscar eventos ${ex.message}")
                 }

            }

        }

       fun busca(busca:String){
           this.busca = busca
           filtroListaEventos()
       }

       fun filtrarCategoria(categoria:String){
            this.categoria = categoria
            filtroListaEventos()
       }

       fun filtrarTipo(tipo:String){
           this.tipo = tipo
           filtroListaEventos()
       }

       private fun filtroListaEventos(){

           val eventosFiltrados = eventos.filter {
                it.title.contains( busca, ignoreCase = true ) &&
                it.category.contains( categoria, ignoreCase = true ) &&
                it.type.contains( tipo, ignoreCase = true )
          }

          _states.value = States.OnSucesso(eventosFiltrados)
      }

       companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                   override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                        val repository = TechEventRepository()
                        return TechEventViewModel(repository) as T
                 }
            }
       }
}