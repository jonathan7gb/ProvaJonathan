package com.example.provajonathan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.provajonathan.R
import com.example.provajonathan.model.ProducaoResultado
import com.google.android.material.button.MaterialButton
import android.widget.TextView

class RelatorioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)

        val txtRelatorio = findViewById<TextView>(R.id.txtRelatorio)
        val btnVoltar = findViewById<MaterialButton>(R.id.btnVoltar)

        val operador = intent.getStringExtra(ProducaoResultado.EXTRA_OPERADOR).orEmpty()
        val linha = intent.getStringExtra(ProducaoResultado.EXTRA_LINHA).orEmpty()
        val pecas = intent.getIntExtra(ProducaoResultado.EXTRA_PECAS, 0)
        val tempoTotalSeg = intent.getLongExtra(ProducaoResultado.EXTRA_TEMPO_TOTAL_SEG, 0L)
        val taktTime = intent.getDoubleExtra(ProducaoResultado.EXTRA_TAKT_TIME, 0.0)

        val tempoFormatado = ProducaoResultado.formatarTempoTotal(tempoTotalSeg)
        val taktFormatado = "%.2f".format(taktTime)

        txtRelatorio.text = getString(
            R.string.relatorio_conteudo,
            operador.uppercase(),
            linha.uppercase(),
            pecas,
            tempoFormatado,
            taktFormatado
        )

        btnVoltar.setOnClickListener { finish() }
    }
}
