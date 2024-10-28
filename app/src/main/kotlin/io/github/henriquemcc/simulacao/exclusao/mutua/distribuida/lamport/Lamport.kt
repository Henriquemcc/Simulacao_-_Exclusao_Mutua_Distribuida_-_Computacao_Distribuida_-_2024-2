package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.lamport

fun main() {
    val algoritmoLamport = AlgoritmoLamport(20)
    algoritmoLamport.start()

    // Finalizando o programa após ele ter sido executado por 1 minuto
    Thread.sleep(60000)
    algoritmoLamport.stopFlag.set(true)
}