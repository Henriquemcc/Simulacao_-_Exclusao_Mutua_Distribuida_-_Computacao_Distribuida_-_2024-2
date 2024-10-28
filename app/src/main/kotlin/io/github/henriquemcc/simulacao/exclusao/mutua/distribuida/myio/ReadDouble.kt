package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio

import java.text.MessageFormat

/**
 * Imprime mensagem de instrução e obtém do usuário um número real no intervalo especificado.
 * @param instructionMessage Mensagem que irá instruir o usuário o que digitar.
 * @param range Intervalo da entrada exigida.
 * @return Valor real digitado pelo usuário.
 */
fun readDouble(instructionMessage: String? = null, range: LongRange? = null): Double {
    var number: Double? = null
    while (number == null || range?.contains(number.toLong()) == false) {
        val stringRead = readString(instructionMessage)
        try {
            number = stringRead.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (range?.contains(number?.toLong()) == false) {
            println(MessageFormat.format("{0} is not in {1}", number, range))
        }
    }

    return number
}