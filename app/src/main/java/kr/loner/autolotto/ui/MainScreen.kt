package kr.loner.autolotto.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.loner.autolotto.R
import kr.loner.autolotto.ui.theme.AutoLottoTheme

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    AutoLottoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()
            val lottoNumbers = uiState.lottoNumbers
            val winningDate = uiState.winningDay
            val mainEffect by viewModel.mainEffect.collectAsStateWithLifecycle()
            val context = LocalContext.current.applicationContext
            LaunchedEffect(mainEffect) {
                when (mainEffect) {
                    is MainEffect.ShowToast -> {
                        val message = (mainEffect as MainEffect.ShowToast).message
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {

                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputLottoNumber(
                        numberValue = lottoNumbers.number01,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER01)
                        }
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.number02,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER02)
                        }
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.number03,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER03)
                        }
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.number04,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER04)
                        }
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.number05,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER05)
                        }
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.number06,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(value?.toShort(), LottoNumberLocation.NUMBER06)
                        }
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "plus"
                    )

                    InputLottoNumber(
                        numberValue = lottoNumbers.bonusNumber,
                        imeAction = ImeAction.Done,
                        onValueChange = { value ->
                            viewModel.setLottoNumber(
                                value?.toShort(),
                                LottoNumberLocation.BONUS_NUMBER
                            )
                        }

                    )
                }

                Button(
                    modifier = Modifier.padding(top = 12.dp),
                    onClick = {
                        viewModel.startRaffle()
                    }) {
                    Text(text = "추첨 시작")
                }

                if (winningDate != null) {
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "당첨 까지 ${winningDate}일이 걸렸습니다.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }

        }
    }
}

@Composable
private fun InputLottoNumber(
    numberValue: Short?,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String?) -> Unit,
) {
    BasicTextField(
        value = numberValue?.toString() ?: "",
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Center,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        ),
        maxLines = 2,
        modifier = Modifier
            .size(32.dp)
            .background(Color.Green, shape = CircleShape),
        onValueChange = { value ->

            if (value.length <= 3 && value.isNotEmpty() && value.toShort() in 1..45) {
                onValueChange(value)
            } else if (value.isEmpty()) {
                onValueChange(null)
            }



        }

    )
}