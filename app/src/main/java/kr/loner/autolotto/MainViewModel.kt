package kr.loner.autolotto

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.loner.autolotto.model.LottoNumbers
import kotlin.math.log
import kotlin.random.Random
import kotlin.random.nextInt

class MainViewModel : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState

    private val _mainEffect = MutableStateFlow<MainEffect>(MainEffect.Ide)
    val mainEffect: StateFlow<MainEffect> = _mainEffect

    fun setLottoNumber(number: Short?, location: LottoNumberLocation) {

        val lottoNumbers = mainUiState.value.lottoNumbers
        _mainUiState.update {
            it.copy(
                lottoNumbers = when (location) {
                    LottoNumberLocation.NUMBER01 -> {
                        lottoNumbers.copy(number01 = number)
                    }

                    LottoNumberLocation.NUMBER02 -> {
                        lottoNumbers.copy(number02 = number)
                    }

                    LottoNumberLocation.NUMBER03 -> {
                        lottoNumbers.copy(number03 = number)
                    }

                    LottoNumberLocation.NUMBER04 -> {
                        lottoNumbers.copy(number04 = number)
                    }

                    LottoNumberLocation.NUMBER05 -> {
                        lottoNumbers.copy(number05 = number)
                    }

                    LottoNumberLocation.NUMBER06 -> {
                        lottoNumbers.copy(number06 = number)
                    }

                    LottoNumberLocation.BONUS_NUMBER -> {
                        lottoNumbers.copy(bonusNumber = number)
                    }
                }
            )
        }
    }

    fun resetLottoNumber() {
        _mainUiState.update {
            it.copy(lottoNumbers = LottoNumbers())
        }
    }

    private fun setWinningDay(day: Long?) {
        _mainUiState.update {
            it.copy(winningDay = day)
        }
    }


    fun startRaffle() {
        val lottoNumbers = mainUiState.value.lottoNumbers
        if (!lottoNumbers.isValidNumbers()) {
            mainEffect(MainEffect.ShowToast("중복 되는 숫자가 없어야 합니다."))
            return
        }

        setWinningDay(null)
        if (lottoNumbers.isAllNumberNotNull) {
            var addWinningDay = 0L
            while (mainUiState.value.winningDay == null) {

                val winningNumbers = LottoNumbers(
                    number01 = Random.nextInt(1..45).toShort(),
                    number02 = Random.nextInt(1..45).toShort(),
                    number03 = Random.nextInt(1..45).toShort(),
                    number04 = Random.nextInt(1..45).toShort(),
                    number05 = Random.nextInt(1..45).toShort(),
                    number06 = Random.nextInt(1..45).toShort(),
                    bonusNumber = Random.nextInt(1..45).toShort(),
                )


                if (checkNumberEach(
                        winningNumbers = winningNumbers,
                        checkNumbers = lottoNumbers
                    )
                ) {
                    setWinningDay(addWinningDay)
                    resetLottoNumber()
                } else {
                    addWinningDay += 7
                }
            }

        } else {
            mainEffect(MainEffect.ShowToast("로또 번호를 모두 선택해주세요"))
        }
    }

    private fun mainEffect(effect: MainEffect) {
        _mainEffect.value = effect
    }

    private fun checkNumberEach(winningNumbers: LottoNumbers, checkNumbers: LottoNumbers): Boolean {
        Log.d("checkk",winningNumbers.toString())
        val winnings = listOf(
            winningNumbers.number01,
            winningNumbers.number02,
            winningNumbers.number03,
            winningNumbers.number04,
            winningNumbers.number05,
            winningNumbers.number06,
            winningNumbers.bonusNumber,
        )

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


}