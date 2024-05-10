package kr.loner.autolotto.model

data class LottoNumbers(
    val number01: Short? = null,
    val number02: Short? = null,
    val number03: Short? = null,
    val number04: Short? = null,
    val number05: Short? = null,
    val number06: Short? = null,
    val bonusNumber: Short? = null,
) {
    val isAllNumberNotNull =
        number01 != null && number02 != null &&
                number03 != null && number04 != null &&
                number05 != null && number06 != null &&
                bonusNumber != null

    fun isValidNumbers(): Boolean {
        val number01Valid =
            (number01 != number02) && (number01 != number03) && (number01 != number04) && (number01 != number05) && (number01 != number06) && (number01 != bonusNumber)

        val number02Valid =
            (number02 != number01) && (number02 != number03) && (number02 != number04) && (number02 != number05) && (number02 != number06) && (number02 != bonusNumber)

        val number03Valid =
            (number03 != number01) && (number03 != number02) && (number03 != number04) && (number03 != number05) && (number03 != number06) && (number03 != bonusNumber)

        val number04Valid =
            (number04 != number01) && (number04 != number02) && (number04 != number03) && (number04 != number05) && (number04 != number06) && (number04 != bonusNumber)

        val number05Valid =
            (number05 != number01) && (number05 != number02) && (number05 != number03) && (number05 != number04) && (number05 != number06) && (number05 != bonusNumber)

        val number06Valid =
            (number06 != number01) && (number06 != number02) && (number06 != number03) && (number06 != number04) && (number06 != number05) && (number06 != bonusNumber)

        val bonusNumberValid =
            (bonusNumber != number01) && (bonusNumber != number02) && (bonusNumber != number03) && (bonusNumber != number04) && (bonusNumber != number05) && (bonusNumber != number06)

        return number01Valid && number02Valid && number03Valid && number04Valid && number05Valid && number06Valid && bonusNumberValid
    }

}
