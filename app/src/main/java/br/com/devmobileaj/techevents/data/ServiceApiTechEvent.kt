package br.com.devmobileaj.techevents.data

import br.com.devmobileaj.techevents.domain.Evento
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApiTechEvent {

    @GET("tech_events.json")
    suspend fun getEvents(): Response<List<Evento>>

}