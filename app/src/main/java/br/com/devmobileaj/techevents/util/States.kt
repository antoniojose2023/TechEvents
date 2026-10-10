package br.com.devmobileaj.techevents.util

import br.com.devmobileaj.techevents.domain.Evento

open class States {
    object Loading : States()
    class OnSucesso(val eventos: List<Evento>) : States()
    class OnError(val mensagem: String) : States()
}