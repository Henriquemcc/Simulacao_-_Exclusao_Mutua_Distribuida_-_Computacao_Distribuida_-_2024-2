package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

import java.util.*

/**
 * Canal de comunicação entre os processos
 */
class CanalComunicacao(numeroProcessos: Int) {

    /**
     * Mensagens a serem envidas
     */
    private val mensagens = Array(numeroProcessos) {
        Collections.synchronizedList(mutableListOf<Mensagem>())
    }

    /**
     * Realiza o envio de mensagens.
     * @param mensagem Mensagem a ser enviada.
     */
    fun enviarMensagem(mensagem: Mensagem) {

        // Broadcast
        if (mensagem.tipo == TipoMensagem.REQUISICAO || mensagem.tipo == TipoMensagem.LIBERACAO)
            for (i in mensagens.indices) {
                if (i != mensagem.processoOrigem) {
                    mensagens[i].add(mensagem)
                }
            }

        // Unicast
        else if (mensagem.tipo == TipoMensagem.RESPOSTA)
            mensagens[mensagem.processoDestino!!].add(mensagem)
    }

    /**
     * Realiza o recebimento de uma mensagem.
     * @return Lista de mensagens recebidas.
     */
    fun receberMensagem(idProcesso: Int): List<Mensagem> {
        val mensagensRecebidas = mutableListOf<Mensagem>()
        while (mensagens[idProcesso].isNotEmpty()){
            mensagensRecebidas.add(mensagens[idProcesso].removeFirst())
        }
        return mensagensRecebidas
    }
}