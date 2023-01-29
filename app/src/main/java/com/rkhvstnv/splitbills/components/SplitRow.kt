package com.rkhvstnv.splitbills.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rkhvstnv.splitbills.R
import kotlinx.coroutines.flow.MutableStateFlow

@Preview(showBackground = true)
@Composable
fun SplitRowPreview(){
    val quantity = MutableStateFlow(1)
    fun inc(){
        quantity.value++
    }
    fun dec(){
        quantity.value--
    }

    SplitRow(
        quantity = quantity.collectAsState(),
        onDecreaseClick = ::dec,
        onIncreaseClick = ::inc,
    )
}


@Composable
fun SplitRow(
    quantity: State<Int>,
    onDecreaseClick: () -> Unit,
    onIncreaseClick: () -> Unit,
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.title_split_by),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onDecreaseClick
        ){
            Icon(
                painter = painterResource(id = R.drawable.remove_24),
                contentDescription = "")
        }

        Text(
            text = quantity.value.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

        IconButton(
            onClick = onIncreaseClick
        ){
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = "")
        }
    }
}