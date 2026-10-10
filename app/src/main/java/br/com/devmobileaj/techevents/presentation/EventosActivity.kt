package br.com.devmobileaj.techevents.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devmobileaj.techevents.R
import br.com.devmobileaj.techevents.databinding.ActivityEventosBinding
import br.com.devmobileaj.techevents.util.States

class EventosActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityEventosBinding.inflate( layoutInflater ) }

    private val techEventViewModel: TechEventViewModel by viewModels()
    private val techEventAdapter = TechEventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
                     Toast.makeText(this, "carregando", Toast.LENGTH_SHORT).show()
                }
                is States.OnSucesso -> {
                    techEventAdapter.addEventos(states.eventos)
                }
                is States.OnError -> {
                    Toast.makeText(this, "Erro - ${states.mensagem}", Toast.LENGTH_SHORT).show()
                }
            }
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
}