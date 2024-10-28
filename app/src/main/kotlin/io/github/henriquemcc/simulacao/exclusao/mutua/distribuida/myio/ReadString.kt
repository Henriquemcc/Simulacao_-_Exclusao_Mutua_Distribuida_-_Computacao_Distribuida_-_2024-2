package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio

/**
 * Imprime mensagem de instrução e obtém do usuário uma string.
 * @param instructionMessage Mensagem que irá instruir o usuário o que digitar.
 * @return String digitada pelo usuário.
 */
fun readString(instructionMessage: String? = null): String {
    if (instructionMessage != null) {
        print(instructionMessage)
    }

    return readLine()?.trim() ?: ""
}