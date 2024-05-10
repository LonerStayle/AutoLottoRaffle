package kr.loner.autolotto

sealed interface MainEffect {
    data object Ide : MainEffect
    data class ShowToast(val message: String) : MainEffect
}