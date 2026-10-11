package br.com.devmobileaj.techevents.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import br.com.devmobileaj.techevents.domain.ITechEventRepository
import br.com.devmobileaj.techevents.util.States

class TechEventRepository: ITechEventRepository {
    private val serviceApiTechEvent = RetrofitHelper.getServiceApiTechEvent()

     /*fun getEvents() = liveData {

          emit(States.Loading)

           try {
               val response = serviceApiTechEvent.getEvents()
               if (response.isSuccessful && response.body() != null) {
                   emit(States.OnSucesso(response.body()!!))
               } else {
                   emit(States.OnError("Erro ao buscar eventos"))
               }

           }catch (ex: Exception){
               Log.i("TAG", "getEvents: ${ex.message}")
           }

     }*/

    override fun getEventRepository(): LiveData<States> {
        return liveData {

            emit(States.Loading)

            try {
                val response = serviceApiTechEvent.getEvents()
                if (response.isSuccessful && response.body() != null) {
                    emit(States.OnSucesso(response.body()!!))
                } else {
                    emit(States.OnError("Erro ao buscar eventos"))
                }

            }catch (ex: Exception){
                Log.i("TAG", "getEvents: ${ex.message}")
            }

        }
    }

}