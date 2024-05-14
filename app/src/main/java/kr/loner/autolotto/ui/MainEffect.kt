package kr.loner.autolotto.ui

sealed interface MainEffect {
    data object Ide : MainEffect
    data class ShowToast(val message: String) : MainEffect
}