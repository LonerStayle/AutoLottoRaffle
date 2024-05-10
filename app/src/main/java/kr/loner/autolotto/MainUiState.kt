package kr.loner.autolotto

import kr.loner.autolotto.model.LottoNumbers

data class MainUiState (
    val winningDay:Long? = null,
    val lottoNumbers:LottoNumbers = LottoNumbers()
)