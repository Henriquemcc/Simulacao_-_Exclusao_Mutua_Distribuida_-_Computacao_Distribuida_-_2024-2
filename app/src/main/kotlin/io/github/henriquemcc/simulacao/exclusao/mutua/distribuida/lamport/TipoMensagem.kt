package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.lamport

/**
 * Enum para representar o tipo de mensagem
 */
enum class TipoMensagem {
    REQUISICAO,
    RESPOSTA,
    LIBERACAO
}