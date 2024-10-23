package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val algoritmoDMutex = AlgoritmoDMutex(20)
    algoritmoDMutex.start()

    // Executando programa por 1 minuto
    Executors.newScheduledThreadPool(1).schedule( {
        println("Finalizando a simulação")
        algoritmoDMutex.stopFlag.set(true)
    }, 1, TimeUnit.MINUTES)

}