package kr.loner.autolotto.ui

import kr.loner.autolotto.model.LottoNumbers

data class MainUiState (
    val winningDay:Long? = null,
    val lottoNumbers:LottoNumbers = LottoNumbers()
)