package kr.loner.autolotto.domain

abstract class BaseNoParamUseCase<out R> {
    abstract operator fun invoke(): R
}