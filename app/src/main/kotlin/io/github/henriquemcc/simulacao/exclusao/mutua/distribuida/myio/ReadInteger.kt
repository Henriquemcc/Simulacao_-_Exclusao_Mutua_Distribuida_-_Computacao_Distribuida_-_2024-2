package io.github.henriquemcc.simulacao.exclusao.mutua.distribuida.myio

import java.text.MessageFormat

/**
 * Imprime mensagem de instrução e obtém do usuário um número inteiro no intervalo especificado.
 * @param instructionMessage Mensagem que irá instruir o usuário o que digitar.
 * @param range Intervalo da entrada exigida.
 * @return Valor inteiro digitado pelo usuário.
 */
fun readInteger(instructionMessage: String? = null, range: IntRange? = null): Int {

    var number: Int? = null
    while (number == null || range?.contains(number) == false) {
        val stringRead = readString(instructionMessage)
        try {
            number = stringRead.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (range?.contains(number) == false) {
            println(MessageFormat.format("{0} is not in {1}", number, range))
        }
    }

    return number
}