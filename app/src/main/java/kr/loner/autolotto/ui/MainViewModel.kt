package kr.loner.autolotto.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.loner.autolotto.domain.CheckWinningNumberUseCase
import kr.loner.autolotto.domain.CreateWinningNumberUseCase
import kr.loner.autolotto.model.LottoNumbers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createWinningNumberUseCase: CreateWinningNumberUseCase,
    private val checkWinningNumberUseCase: CheckWinningNumberUseCase
) : ViewModel() {

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

    private fun resetLottoNumber() {
        _mainUiState.update {
            it.copy(lottoNumbers = LottoNumbers())
        }
    }

    private fun setWinningDay(day: Long?) {
        _mainUiState.update {
            it.copy(winningDay = day)
        }
    }

    private var raffleJob: Job? = null
    fun startRaffle() {

        if (raffleJob?.isActive == true) return

        val lottoNumbers = mainUiState.value.lottoNumbers
        if (!lottoNumbers.isValidNumbers()) {
            mainEffect(MainEffect.ShowToast("중복 되는 숫자가 없어야 합니다."))
            return
        }

        if (lottoNumbers.isAllNumberNotNull) {
            raffleJob = viewModelScope.launch(Dispatchers.Default) {
                setWinningDay(null)
                var addWinningDay = 0L
                while (isActive) {


                    val winningNumbers = createWinningNumberUseCase()

                    if (checkNumberEach(
                            winningNumbers = winningNumbers,
                            checkNumbers = lottoNumbers
                        )
                    ) {
                        Log.d("checkk", "당첨!!!")
                        withContext(Dispatchers.Main) {
                            setWinningDay(addWinningDay)
                            resetLottoNumber()
                        }
                        break
                    } else {
                        addWinningDay += 7
                    }
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