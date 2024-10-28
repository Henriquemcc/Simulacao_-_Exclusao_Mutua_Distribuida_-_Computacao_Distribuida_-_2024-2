package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.lamport

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
): Comparable<Mensagem> {
    override fun compareTo(other: Mensagem): Int {
        return this.timestampOrigem.compareTo(other.timestampOrigem)
    }

}