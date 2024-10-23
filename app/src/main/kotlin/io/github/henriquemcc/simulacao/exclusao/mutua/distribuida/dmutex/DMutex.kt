package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

fun main() {
    val algoritmoDMutex = AlgoritmoDMutex(20)
    algoritmoDMutex.run()
}