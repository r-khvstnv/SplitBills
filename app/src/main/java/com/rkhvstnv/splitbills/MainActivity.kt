package com.rkhvstnv.splitbills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rkhvstnv.splitbills.components.InputField
import com.rkhvstnv.splitbills.components.PerPersonCard
import com.rkhvstnv.splitbills.components.SplitRow
import com.rkhvstnv.splitbills.components.TipsBox
import com.rkhvstnv.splitbills.ui.theme.SplitBillsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplitBillsTheme {
                MainRoot {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PerPersonCard(totalPerPerson = viewModel.perPersonBill.collectAsState())
                        MainBody(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MainRoot(content: @Composable () -> Unit){
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colorScheme.background) {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun MainRootPreview(){
    val viewModel: MainViewModel = viewModel()

    SplitBillsTheme {
        MainRoot {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PerPersonCard(totalPerPerson = viewModel.perPersonBill.collectAsState())
                MainBody(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainBody(viewModel: MainViewModel){
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.m_default_margin))
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.m_card_corner_radius)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.card_content_padding))
        ) {

            SplitRow(
                quantity = viewModel.personQuantity.collectAsState(),
                onDecreaseClick = viewModel::decreasePersonQuantity,
                onIncreaseClick = viewModel::increasePersonQuantity
            )

            Spacer(modifier = Modifier.padding(20.dp))

            TipsBox(
                progress = viewModel.tipsPercent.collectAsState(),
                onProgressChanged = viewModel::updateTipsPercent
            )


            val billValueState = viewModel.billValue.collectAsState()
            val keyboardController = LocalSoftwareKeyboardController.current
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = billValueState,
                label = stringResource(id = R.string.label_bill),
                onValueChanged = viewModel::updateBillValueAndCalculate,
                isEnabled = true,
                onAction = KeyboardActions{
                    if (billValueState.value.isNotEmpty()){
                        viewModel.updatePerPersonBill()
                        keyboardController?.hide()
                    }
                }
            )
        }

    }
}


