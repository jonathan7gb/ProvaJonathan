package com.example.provajonathan.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.provajonathan.R
import com.example.provajonathan.model.ProducaoResultado
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var edtOperador: TextInputEditText
    private lateinit var edtLinha: TextInputEditText
    private lateinit var layoutOperador: TextInputLayout
    private lateinit var layoutLinha: TextInputLayout
    private lateinit var txtContador: TextView
    private lateinit var btnIniciar: MaterialButton
    private lateinit var btnRegistrar: MaterialButton
    private lateinit var btnFinalizar: MaterialButton

    private var contador = 0
    private var instanteInicialMs = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtOperador = findViewById(R.id.edtOperador)
        edtLinha = findViewById(R.id.edtLinha)
        layoutOperador = findViewById(R.id.layoutOperador)
        layoutLinha = findViewById(R.id.layoutLinha)
        txtContador = findViewById(R.id.txtContador)
        btnIniciar = findViewById(R.id.btnIniciar)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnFinalizar = findViewById(R.id.btnFinalizar)

        btnIniciar.setOnClickListener { iniciarProducao() }
        btnRegistrar.setOnClickListener { registrarPeca() }
        btnFinalizar.setOnClickListener { finalizarProducao() }
    }

    private fun iniciarProducao() {
        val operador = edtOperador.text.toString().trim()
        val linha = edtLinha.text.toString().trim()

        if (operador.isEmpty() || linha.isEmpty()) {
            Toast.makeText(this, R.string.erro_campos_vazios, Toast.LENGTH_SHORT).show()
            return
        }

        instanteInicialMs = System.currentTimeMillis()
        contador = 0
        txtContador.text = getString(R.string.contador_valor, contador)

        layoutOperador.isEnabled = false
        layoutLinha.isEnabled = false

        btnRegistrar.isEnabled = true
        btnIniciar.isEnabled = false
    }

    private fun registrarPeca() {
        contador++
        txtContador.text = getString(R.string.contador_valor, contador)
    }

    private fun finalizarProducao() {
        if (instanteInicialMs == 0L) {
            Toast.makeText(this, R.string.erro_nao_iniciado, Toast.LENGTH_SHORT).show()
            return
        }

        val instanteFinalMs = System.currentTimeMillis()
        val tempoTotalMs = instanteFinalMs - instanteInicialMs
        val tempoTotalSeg = tempoTotalMs / 1000
        val taktTime = ProducaoResultado.calcularTaktTime(tempoTotalMs, contador)

        val intent = Intent(this, RelatorioActivity::class.java).apply {
            putExtra(ProducaoResultado.EXTRA_OPERADOR, edtOperador.text.toString().trim())
            putExtra(ProducaoResultado.EXTRA_LINHA, edtLinha.text.toString().trim())
            putExtra(ProducaoResultado.EXTRA_PECAS, contador)
            putExtra(ProducaoResultado.EXTRA_TEMPO_TOTAL_SEG, tempoTotalSeg)
            putExtra(ProducaoResultado.EXTRA_TAKT_TIME, taktTime)
        }

        startActivity(intent)
    }
}
