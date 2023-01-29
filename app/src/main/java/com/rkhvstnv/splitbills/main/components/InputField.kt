package com.rkhvstnv.splitbills.main.components

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

@Preview(showBackground = true)
@Composable
fun InputFieldPreview(){
    val mValue = MutableStateFlow("")

    fun updateValue(input: String){
        mValue.value = input
    }

    InputField(
        valueState = mValue.collectAsState(),
        label = "Preview Label",
        onValueChanged = ::updateValue,
    )
}

/**
 *  Method shows [OutlinedTextField] with minor customisation.
 *  Applicable only for [KeyboardType.Number] input.
 *
 *  Operating value is [String]
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    /**Value that displaying in [OutlinedTextField]*/
    valueState: State<String>,
    /**Label for [OutlinedTextField]*/
    label: String,
    /**Method for handling [String] after every user interaction*/
    onValueChanged: (String) -> Unit,
    /**State of enabling [OutlinedTextField]*/
    isEnabled: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
){
    OutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = { 
                input ->
            //Handle max length of the String
            if (input.length < 10){
                onValueChanged(input)
            }
        },
        label = { Text(text = label)},
        leadingIcon = {
            //Custom icon
            Icon(
                painter = painterResource(id = R.drawable.ic_currency_bitcoin_24),
                contentDescription = "Currency Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
        keyboardActions = onAction,
        enabled = isEnabled
    )

}