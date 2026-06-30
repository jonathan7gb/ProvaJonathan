package com.example.provajonathan.model

object ProducaoResultado {

    const val EXTRA_OPERADOR = "operador"
    const val EXTRA_LINHA = "linha"
    const val EXTRA_PECAS = "pecas"
    const val EXTRA_TEMPO_TOTAL_SEG = "tempoTotalSeg"
    const val EXTRA_TAKT_TIME = "taktTime"

    fun calcularTaktTime(tempoTotalMs: Long, quantidadePecas: Int): Double {
        if (quantidadePecas <= 0) return 0.0
        return (tempoTotalMs.toDouble() / quantidadePecas) / 1000.0
    }

    fun formatarTempoTotal(segundos: Long): String {
        val minutos = segundos / 60
        val segs = segundos % 60
        return "$minutos min $segs seg"
    }
}
