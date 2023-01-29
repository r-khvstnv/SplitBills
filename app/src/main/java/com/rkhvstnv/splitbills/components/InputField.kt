package com.rkhvstnv.splitbills.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.rkhvstnv.splitbills.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Preview(showBackground = true)
@Composable
fun InputFieldPreview(){
    val _mValue = MutableStateFlow("")
    val mValue: StateFlow<String> = _mValue.asStateFlow()

    fun updateValue(input: String){
        _mValue.value = input
    }

    InputField(
        valueState = mValue.collectAsState(),
        label = "Preview Label",
        onValueChanged = ::updateValue,
        keyboardType =  KeyboardType.Number
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: State<String>,
    label: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
){
    OutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = onValueChanged,
        singleLine = isSingleLine,
        label = { Text(text = label)},
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.currency_bitcoin_24),
                contentDescription = "Currency Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )

}