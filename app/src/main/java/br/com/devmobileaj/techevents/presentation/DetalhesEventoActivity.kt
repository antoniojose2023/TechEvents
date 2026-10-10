package br.com.devmobileaj.techevents.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobileaj.techevents.R
import br.com.devmobileaj.techevents.databinding.ActivityDetalhesEventoBinding
import br.com.devmobileaj.techevents.domain.Evento
import coil.load

class DetalhesEventoActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityDetalhesEventoBinding.inflate(layoutInflater) }

    private lateinit var evento: Evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivVoltar.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        if(bundle != null){
               evento = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    bundle.getSerializable("evento", Evento::class.java)!!
               }else{
                    bundle.getSerializable("evento") as Evento
               }

               popularTelaDetalhes(evento)
        }
    }

    fun popularTelaDetalhes(evento: Evento){

          binding.apply {
               tvTituloEventoDetalhe.text = evento.title
               tvCidadeEventoDetalhe.text = evento.city
               chipcategoriaEventoDetalhe.text = evento.category
               chipTipoEventoDetalhe.text = evento.type
               tvDataEventoDetalhe.text = evento.date
               tvHoraInicialEventodetalhe.text = evento.startTime
               tvHoraFinalEventodetalhe.text = evento.endTime
               tvLocalEventoDetalhe.text = evento.localeSpec
               tvEnderecoEventoDetalhe.text = evento.address
               tvCidadeEstadoEeventoDetalhe.text = "${evento.city} - ${evento.state}"
               tvOrganizadorEventoDetalhe.text = evento.organizer
               tvDescricaoEventoDetalhe.text = evento.description

               ivEventoDetalhe.load( evento.eventUrl )
          }

         val categoria = binding.chipcategoriaEventoDetalhe.text.toString()
         when(categoria){
             "Android" -> {
                 binding.chipcategoriaEventoDetalhe.chipBackgroundColor = getColorStateList( R.color.verde )
             }
             "Flutter" ->{
                 binding.chipcategoriaEventoDetalhe.chipBackgroundColor = getColorStateList( R.color.laranja )
             }
             "Kotlin" -> {
                 binding.chipcategoriaEventoDetalhe.chipBackgroundColor = getColorStateList( R.color.azul )
             }
         }

        val tipo = binding.chipTipoEventoDetalhe.text.toString()
        when(tipo){
            "PRESENCIAL" -> {
                binding.chipTipoEventoDetalhe.chipBackgroundColor = getColorStateList( R.color.azul_escuro )
            }
            "ONLINE" ->{
                binding.chipTipoEventoDetalhe.chipBackgroundColor = getColorStateList( R.color.verde_claro )
            }

        }
    }
}