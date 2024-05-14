package kr.loner.autolotto.domain

import kr.loner.autolotto.model.LottoNumbers
import java.security.SecureRandom
import javax.inject.Inject

class CreateWinningNumberUseCase @Inject constructor(private val random: SecureRandom) :
    BaseNoParamUseCase<LottoNumbers>() {
    override fun invoke() = LottoNumbers(
        number01 = (random.nextInt(45) + 1).toShort(),
        number02 = (random.nextInt(45) + 1).toShort(),
        number03 = (random.nextInt(45) + 1).toShort(),
        number04 = (random.nextInt(45) + 1).toShort(),
        number05 = (random.nextInt(45) + 1).toShort(),
        number06 = (random.nextInt(45) + 1).toShort(),
        bonusNumber = (random.nextInt(45) + 1).toShort(),
    )
}