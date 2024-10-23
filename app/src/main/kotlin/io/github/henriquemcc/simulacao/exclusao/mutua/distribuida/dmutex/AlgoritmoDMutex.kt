package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Classe que armazena as principais variáveis do algoritmo DMutex.
 * @param numeroProcessos Número de processos a serem executados.
 */
class AlgoritmoDMutex(
    val numeroProcessos: Int
) {

    /**
     * Flag que as threads dos processos vão olhar para finalizarem a execução.
     */
    var stopFlag = AtomicBoolean(false)

    /**
     * Lista de processos que serão executados.
     */
    private val processos = sortedSetOf<Processo>()

    /**
     * Canal de comunicação a ser utilizado pelos processos.
     */
    val canalComunicacao = CanalComunicacao(numeroProcessos)

    /**
     * Construtor da classe DMutex.
     */
    init {

        // Criando os processos
        for (i in 0..<numeroProcessos) {
            processos.add(Processo(i, this))
        }
    }

    /**
     * Executa os processos.
     */
    fun run() {
        for (processo in processos)
            processo.start()
    }
}