package br.com.devmobileaj.techevents.domain

import android.util.Log
import androidx.lifecycle.liveData
import br.com.devmobileaj.techevents.data.RetrofitHelper
import br.com.devmobileaj.techevents.util.States
import kotlin.math.log

class TechEventRepository {
    private val serviceApiTechEvent = RetrofitHelper.getServiceApiTechEvent()


     fun getEvents() = liveData {

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