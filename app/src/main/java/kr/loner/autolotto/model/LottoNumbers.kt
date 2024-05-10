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
}
