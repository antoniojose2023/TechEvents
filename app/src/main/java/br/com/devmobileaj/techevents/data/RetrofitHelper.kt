package br.com.devmobileaj.techevents.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object{

        private const val BASE_URL = "https://antoniojose2023.github.io/api_tech_events/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getServiceApiTechEvent(): ServiceApiTechEvent {
              return retrofit.create(ServiceApiTechEvent::class.java)
        }

    }

}