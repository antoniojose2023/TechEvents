package br.com.devmobileaj.techevents.domain

import androidx.lifecycle.LiveData
import br.com.devmobileaj.techevents.util.States

interface ITechEventRepository {
  suspend  fun getEventRepository(): List<Evento>

}