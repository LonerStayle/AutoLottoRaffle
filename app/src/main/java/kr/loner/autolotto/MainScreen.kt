package kr.loner.autolotto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.loner.autolotto.ui.theme.AutoLottoTheme

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    AutoLottoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val lottoNumbers by viewModel.lottoNumbers.collectAsStateWithLifecycle()
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputLottoNumber(
                    numberValue = lottoNumbers.number02,
                    onValueChange = { value ->
                        viewModel.setLottoNumber02(value.toShort())
                    }
                )
                InputLottoNumber(
                    numberValue = lottoNumbers.number03,
                    onValueChange = { value ->
                        viewModel.setLottoNumber03(value.toShort())
                    }
                )
                InputLottoNumber(
                    numberValue = lottoNumbers.number04,
                    onValueChange = { value ->
                        viewModel.setLottoNumber04(value.toShort())
                    }
                )
                InputLottoNumber(
                    numberValue = lottoNumbers.number05,
                    onValueChange = { value ->
                        viewModel.setLottoNumber05(value.toShort())
                    }
                )

                InputLottoNumber(
                    numberValue = lottoNumbers.number06,
                    onValueChange = { value ->
                        viewModel.setLottoNumber06(value.toShort())
                    }
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "plus"
                )

                InputLottoNumber(
                    numberValue = lottoNumbers.bonusNumber,
                    onValueChange = { value ->
                        viewModel.setLottoBonusNumber(value.toShort())
                    }
                )
            }
        }
    }
}

@Composable
private fun InputLottoNumber(
    numberValue: Short?,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        value = numberValue?.toString() ?: "",
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Center,
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        maxLines = 2,
        modifier = Modifier
            .size(32.dp)
            .background(Color.Green, shape = CircleShape),
        onValueChange = { value ->
            if(value.length < 3) return@BasicTextField
            onValueChange(value)
        }

    )
}