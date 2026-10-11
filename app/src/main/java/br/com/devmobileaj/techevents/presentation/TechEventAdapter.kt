package br.com.devmobileaj.techevents.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.devmobileaj.techevents.R
import br.com.devmobileaj.techevents.databinding.ItemEventoBinding
import br.com.devmobileaj.techevents.domain.Evento

class TechEventAdapter(var onClickEventos: (Evento) -> Unit = {}): RecyclerView.Adapter<TechEventAdapter.TechEventViewHolder>() {

    private var eventos = mutableListOf<Evento>()

    fun addEventos(lista: List<Evento>){
          eventos = lista.toMutableList()
          notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TechEventViewHolder {

          val inflate = LayoutInflater.from(parent.context)
          val binding = ItemEventoBinding.inflate(inflate, parent, false)
          return TechEventViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TechEventViewHolder,
        position: Int
    ) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    override fun getItemCount() = eventos.size

    inner class TechEventViewHolder(val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(evento: Evento){

             binding.apply {
                  tvCategoriaItem.text = evento.category
                  tvTituloEventoItem.text = evento.title
                  tvDataEventoItem.text = evento.date
                  tvHoraEventoItem.text = evento.startTime
                  tvLocalEventoItem.text = evento.localeSpec
                  tvCidadeEventoItem.text = evento.city
                  tvEstadoEventoItem.text = evento.state
                  tvPrecoEventoItem.text = evento.price
                  tvTipoEventoItem.text = evento.type
             }

             when(evento.category){
                 "Android" ->{
                     binding.tvCategoriaItem.background = ContextCompat.getDrawable(itemView.context, R.drawable.fundo_tv_categoria_android)
                 }
                 "Flutter" ->{
                     binding.tvCategoriaItem.background = ContextCompat.getDrawable(itemView.context, R.drawable.fundo_tv_categoria_flutter)
                 }
                 "Kotlin" ->{
                     binding.tvCategoriaItem.background = ContextCompat.getDrawable(itemView.context, R.drawable.fundo_tv_categoria_kotlin)
                 }


             }

             itemView.setOnClickListener {
                   onClickEventos(evento)
             }

        }
    }

}