package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.dmutex

class Mensagem(
    /**
     * Id do processo que enviou a mensagem.
     */
    val processoOrigem: Int,

    /**
     * Id do processo que receberá a mensagem.
     */
    val processoDestino: Int? = null,

    /**
     * Timestamp do processo que enviou a mensagem.
     */
    val timestampOrigem: Long,

    /**
     * Tipo de mensagem enviada.
     */
    val tipo: TipoMensagem
)