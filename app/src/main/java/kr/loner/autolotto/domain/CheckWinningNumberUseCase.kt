package kr.loner.autolotto.domain

import kr.loner.autolotto.model.LottoNumbers

class CheckWinningNumberUseCase(
) : BaseUseCase<CheckWinningNumberUseCase.CheckWinningNumberParams, Boolean>() {

    override fun invoke(params: CheckWinningNumberParams): Boolean {
        val winningNumbers = params.winningNumbers
        val winnings = listOf(
            winningNumbers.number01,
            winningNumbers.number02,
            winningNumbers.number03,
            winningNumbers.number04,
            winningNumbers.number05,
            winningNumbers.number06,
            winningNumbers.bonusNumber,
        )

        val checkNumbers = params.checkNumbers
        val checks = listOf(
            checkNumbers.number01,
            checkNumbers.number02,
            checkNumbers.number03,
            checkNumbers.number04,
            checkNumbers.number05,
            checkNumbers.number06,
            checkNumbers.bonusNumber,
        )

        val results = mutableListOf<Boolean>()

        winnings.forEach { winning ->
            results.add(checks.find { check -> winning == check } != null)
        }

        return results.all { it }
    }

    data class CheckWinningNumberParams(
        val winningNumbers: LottoNumbers,
        val checkNumbers: LottoNumbers,
    )
}