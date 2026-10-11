package br.com.devmobileaj.techevents.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devmobileaj.techevents.R
import br.com.devmobileaj.techevents.data.TechEventRepository
import br.com.devmobileaj.techevents.databinding.ActivityEventosBinding
import br.com.devmobileaj.techevents.domain.Evento
import br.com.devmobileaj.techevents.domain.ITechEventRepository
import br.com.devmobileaj.techevents.presentation.TechEventAdapter
import br.com.devmobileaj.techevents.presentation.viewmodel.TechEventViewModel
import br.com.devmobileaj.techevents.util.States
import com.google.android.material.chip.Chip

class EventosActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityEventosBinding.inflate( layoutInflater ) }

    val techEventViewModel: TechEventViewModel by viewModels{
          TechEventViewModel.Factory
    }
    private val techEventAdapter = TechEventAdapter()

    private var listaEventos = mutableListOf<Evento>()

    private var statusListaRecyclerView = false

    private var pagina = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //simulação paginação - pois essa api é fake e não tem paginação.
        statusListaRecyclerView = binding.rvEventos.canScrollVertically(1)
        if(!statusListaRecyclerView){
              //chamar o metodo para incrementar a pagina
              incrementarPagina()
        }



        //consulta pelo edittext
        binding.editPesquisa.setOnEditorActionListener { v, actionId, event ->
             if(actionId == EditorInfo.IME_ACTION_SEARCH){
                  val texto = binding.editPesquisa.text.toString()
                  getListaFiltradaSearViewPorNomeEvento( texto )
             }

            true
        }


        //consulta pelo chipGroupCategoria
        binding.chipGroupCategoria.setOnCheckedStateChangeListener { group, checkedIds ->
             val id = checkedIds.firstOrNull()
             val chip =  id?.let { group.findViewById<Chip>( it ) }
             val texto = chip?.text.toString() ?: ""
             getListaFiltradaChipPorCategoriaEvento( texto )

        }


        //consulta pelo chipGroupTipoEvento
        binding.chipGroupTipoEvento.setOnCheckedStateChangeListener { group, checkedIds ->
            val id = checkedIds.firstOrNull()
            val chip = id?.let { group.findViewById<Chip>( it ) }
            val texto = chip?.text.toString() ?: ""
            getListaFiltradaChipPorTipoEvento( texto )

        }

        techEventAdapter.onClickEventos = { evento ->
            val intent = Intent(this, DetalhesEventoActivity::class.java)
            intent.putExtra("evento", evento)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out )
        }

        configurarRecyclerView()

        techEventViewModel.getEvents().observe(this){ states ->
            when(states){
                is States.Loading -> {
                     binding.progressBarCircula.visibility = View.VISIBLE
                }
                is States.OnSucesso -> {
                    listaEventos = states.eventos.toMutableList()
                    techEventAdapter.addEventos(states.eventos)
                    binding.progressBarCircula.visibility = View.GONE
                }
                is States.OnError -> {
                    Toast.makeText(this, "Erro - ${states.mensagem}", Toast.LENGTH_SHORT).show()
                    binding.progressBarCircula.visibility = View.GONE
                    binding.containerTelaError.visibility = View.VISIBLE
                }
            }
        }
    }

    fun incrementarPagina(){
          if(pagina <= 10){
              //chama o metodo para retorna a pagina 1
              //depois incrementa a pagina
              pagina++
              Log.i("TAG", "incrementarPagina: $pagina")
          }
    }


    override fun onStart() {
        super.onStart()
        configurarRecyclerView()
    }


    fun configurarRecyclerView(){
          binding.rvEventos.layoutManager = LinearLayoutManager(this)
          binding.rvEventos.adapter = techEventAdapter
    }

    fun getListaFiltradaSearViewPorNomeEvento(textoPesquisa: String){
         val listaFiltrada = if(textoPesquisa.isNullOrEmpty()){
               listaEventos
         }else{
               listaEventos.filter{ it ->
                    it.title.contains( textoPesquisa )
               }
         }


         techEventAdapter.addEventos( listaFiltrada )
    }

    fun getListaFiltradaChipPorCategoriaEvento(textoPesquisa: String){
        val listaFiltrada = if(textoPesquisa.isNullOrEmpty()){
            listaEventos
        }else{
            listaEventos.filter{ it ->
                it.category.contains( textoPesquisa )
            }
        }

        if(listaFiltrada.isEmpty()){
            binding.containerTelaError.visibility = View.VISIBLE
        }else{
            binding.containerTelaError.visibility = View.GONE
        }

        techEventAdapter.addEventos( listaFiltrada )
    }

    fun getListaFiltradaChipPorTipoEvento(textoPesquisa: String){
        val listaFiltrada = if(textoPesquisa.isNullOrEmpty()){
            listaEventos
        }else{
            listaEventos.filter{ it ->
                it.type.contains( textoPesquisa )
            }
        }

        if(listaFiltrada.isEmpty()){
            binding.containerTelaError.visibility = View.VISIBLE
        }else{
            binding.containerTelaError.visibility = View.GONE
        }

        techEventAdapter.addEventos( listaFiltrada )
    }


}