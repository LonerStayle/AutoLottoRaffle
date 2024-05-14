package kr.loner.autolotto.domain
abstract class BaseUseCase<in T, out R> {
    abstract operator fun invoke(params: T): R
}