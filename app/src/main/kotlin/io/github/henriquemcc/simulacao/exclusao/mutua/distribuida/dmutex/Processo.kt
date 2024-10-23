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
): Thread(), Comparable<Processo> {

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
    private var entradaAreaCritica = Array<Boolean>(algoritmoDMutex.numeroProcessos) {
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
                println("Processo $id recebe mensagem de processo ${mensagem.processoOrigem} do tipo ${mensagem.tipo}, com timestamp ${mensagem.timestampOrigem} em ${relogio.get()}")
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

    private fun enviarMensagem(mensagem: Mensagem) {
        println("Processo ${mensagem.processoOrigem} envia mensagem para processo ${mensagem.processoDestino} do tipo ${mensagem.tipo}, com timestamp ${mensagem.timestampOrigem}")
        algoritmoDMutex.canalComunicacao.enviarMensagem(mensagem)
    }

    /**
     * Ação executada pelo processo.
     */
    override fun run () {
        println("Processo $id está em execução")
        daemonMensagens.start()

        while (!algoritmoDMutex.stopFlag.get()) {
            sleep(Random.nextLong(15000))
            relogio.set(relogio.get()+1)

            // Entrando na área crítica
            if (Random.nextBoolean()) {
                println("Processo $id quer acessar á área crítica em ${relogio.get()}")
                entradaAreaCritica = Array<Boolean>(algoritmoDMutex.numeroProcessos) {
                    false
                }
                entradaAreaCritica[id] = true
                enviarMensagem(Mensagem(id, Int.MAX_VALUE, relogio.get(), TipoMensagem.REQUISICAO))

                // Esperando entrar na área crítica
                while (!entradaaAreaCriticaPermitida()) {
                    sleep(1000)
                    relogio.set(relogio.get()+1)
                }


                // Na área crítica
                relogio.set(relogio.get()+1)
                println("Processo $id está acessando a área crítica em ${relogio.get()}")
                while (Random.nextBoolean()) {
                    sleep(1000)
                    relogio.set(relogio.get()+1)
                }

                // Saindo da área crítica
                relogio.set(relogio.get()+1)
                println("Processo $id quer sair da área crítica em ${relogio.get()}")
                enviarMensagem(Mensagem(id, Int.MAX_VALUE, relogio.get(), TipoMensagem.LIBERACAO))
            }
        }
    }

    /**
     * Compara um Processo com outro.
     * @param other Outro processo a ser comparado com este.
     * @return Retorna 0 se esta instância for igual, um número negativo se esta instância for menor e um número positivo se esta instância for maior que a outra.
     */
    override fun compareTo(other: Processo): Int {
        return this.id.compareTo(other.id)
    }


}