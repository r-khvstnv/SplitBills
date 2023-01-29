package com.rkhvstnv.splitbills.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rkhvstnv.splitbills.R
import kotlinx.coroutines.flow.MutableStateFlow

@Preview(showBackground = true)
@Composable
fun TipsBoxPreview(){
    val progress = MutableStateFlow(0f)
    fun onPrChange(input: Float){
        progress.value = input
    }

    TipsBox(
        progress = progress.collectAsState(),
        onProgressChanged = ::onPrChange,
    )

}

@Composable
fun TipsBox(
    progress: State<Float>,
    onProgressChanged: (Float) -> Unit,
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.title_tips),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            val displayedProgress: Int = (progress.value * 100).toInt()
            Text(
                text = "$displayedProgress %",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Slider(
            value = progress.value,
            onValueChange = onProgressChanged,
        )
    }

}