package kr.loner.autolotto

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.loner.autolotto.model.LottoNumbers

class MainViewModel : ViewModel() {

    private val _lottoNumbers = MutableStateFlow(LottoNumbers())
    val lottoNumbers: StateFlow<LottoNumbers> = _lottoNumbers

    private val _mainEffect = MutableStateFlow<MainEffect>(MainEffect.Ide)
    val mainEffect: StateFlow<MainEffect> = _mainEffect

    fun setLottoNumber01(number01: Short?) {
        _lottoNumbers.update { it.copy(number01 = number01) }
    }

    fun setLottoNumber02(number02: Short?) {
        _lottoNumbers.update { it.copy(number02 = number02) }
    }

    fun setLottoNumber03(number03: Short?) {
        _lottoNumbers.update { it.copy(number03 = number03) }
    }

    fun setLottoNumber04(number04: Short?) {
        _lottoNumbers.update { it.copy(number04 = number04) }
    }

    fun setLottoNumber05(number05: Short?) {
        _lottoNumbers.update { it.copy(number05 = number05) }
    }

    fun setLottoNumber06(number06: Short?) {
        _lottoNumbers.update { it.copy(number06 = number06) }
    }

    fun setLottoBonusNumber(bonusNumber: Short?) {
        _lottoNumbers.update { it.copy(bonusNumber = bonusNumber) }
    }

    fun startRaffle() {
        val lottoNumbers = lottoNumbers.value
        if (lottoNumbers.isAllNumberNotNull) {

        } else {

        }
    }
}