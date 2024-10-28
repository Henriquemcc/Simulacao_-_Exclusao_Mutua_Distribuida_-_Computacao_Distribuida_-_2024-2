package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.lamport

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val algoritmoLamport = AlgoritmoLamport(20)
    algoritmoLamport.start()

    // Executando programa por 1 minuto
    Executors.newScheduledThreadPool(1).schedule( {
        println("Finalizando a simulação")
        algoritmoLamport.stopFlag.set(true)
        println(algoritmoLamport.stopFlag.get())
    }, 5, TimeUnit.SECONDS)

}