package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.lamport

import io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio.printHeader
import io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio.readDouble
import io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio.readInteger
import io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio.readString
import kotlin.math.floor

fun main() {

    // Imprimindo cabeçalho
    printHeader("Algoritmo de Lamport")

    // Obtendo quantidade de processos
    val numeroProcessos = readInteger("Número de processos: ", IntRange(1, Int.MAX_VALUE))

    // Obtendo o tempo de simulação
    val tempoSimulacao = readDouble("Tempo de simulação (em segundos): ", LongRange(1, Double.MAX_VALUE.toLong()))

    // Criando uma instância do algoritmo de Lamport
    val algoritmoLamport = AlgoritmoLamport(numeroProcessos)

    // Iniciando a simulação
    algoritmoLamport.start()

    // Finalizando o programa após ele ter sido executado pelo tempo especificado
    Thread.sleep(floor(tempoSimulacao * 1000).toLong())
    algoritmoLamport.stopFlag.set(true)
}