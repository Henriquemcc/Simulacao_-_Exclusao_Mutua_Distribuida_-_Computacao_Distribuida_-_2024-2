package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio

/**
 * Imprime mensagem de instrução e obtém do usuário um número real no intervalo especificado.
 * @param instructionMessage Mensagem que irá instruir o usuário o que digitar.
 * @return Valor real digitado pelo usuário.
 */
fun readDouble(instructionMessage: String? = null): Double {

    var number: Double? = null
    while (number == null) {
        val stringRead = readString(instructionMessage)
        try {
            number = stringRead.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return number
}