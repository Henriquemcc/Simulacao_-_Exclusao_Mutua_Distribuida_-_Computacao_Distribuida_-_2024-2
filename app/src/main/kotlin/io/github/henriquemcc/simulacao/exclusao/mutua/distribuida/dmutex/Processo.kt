package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

import java.util.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.max
import kotlin.random.Random

/**
 * Classe que representa um processo.
 * @param id ID do processo.
 * @param algoritmoDMutex Classe que instanciou o processo.
 */
class Processo(
    private val id: Int,
    private val algoritmoDMutex: AlgoritmoDMutex,
): Thread() {

    /**
     * Fila de requisições do DMutex.
     */
    private val filaRequisicoes: SortedSet<Mensagem> = Collections.synchronizedSortedSet(sortedSetOf())

    /**
     * Relógio lógico do processo.
     */
    private var relogio = AtomicLong(0)

    /**
     * Array para controle da entrada na área crítica
     */
    private val entradaAreaCritica = Array<Boolean>(algoritmoDMutex.numeroProcessos) {
        false
    }

    /**
     * Verifica se pode entrar na área crítica.
     */
    private fun entradaaAreaCriticaPermitida()  = !entradaAreaCritica.contains(false)

    /**
     * Thread responsável por receber requisições e enviar respostas
     */
    private val daemonMensagens = Thread {

        var processoComecoFila = -1

        while (!algoritmoDMutex.stopFlag.get()) {
            sleep(Random.nextLong(1000))
            val mensagens = algoritmoDMutex.canalComunicacao.receberMensagem(id)
            for (mensagem in mensagens) {
                relogio.set(max(relogio.get(), mensagem.timestampOrigem) +1)
                when (mensagem.tipo) {
                    TipoMensagem.REQUISICAO -> {
                        filaRequisicoes.add(mensagem)
                    }
                    TipoMensagem.RESPOSTA -> {
                        entradaAreaCritica[mensagem.processoOrigem] = true
                    }
                    TipoMensagem.LIBERACAO -> {
                        processoComecoFila = -1
                        filaRequisicoes.removeFirst()
                    }
                }
            }
            if (filaRequisicoes.isNotEmpty() && processoComecoFila != filaRequisicoes.first().processoOrigem) {
                processoComecoFila = filaRequisicoes.first().processoOrigem
                algoritmoDMutex.canalComunicacao.enviarMensagem(Mensagem(id, filaRequisicoes.first().processoOrigem, relogio.get(), TipoMensagem.RESPOSTA))
            }

        }
    }

    /**
     * Ação executada pelo processo.
     */
    override fun run () {
        println("Processo $id está em execução")
        daemonMensagens.start()
    }


}