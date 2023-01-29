package com.rkhvstnv.splitbills.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rkhvstnv.splitbills.R
import kotlinx.coroutines.flow.MutableStateFlow

@Preview(showBackground = true)
@Composable
fun PerPersonCardPreview(){
    val bill = MutableStateFlow(123.4567)
    PerPersonCard(totalPerPerson = bill.collectAsState())
}



@Composable
fun PerPersonCard(
    totalPerPerson: State<Double>
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.card_per_person_height))
            .padding(dimensionResource(id = R.dimen.m_default_margin))
        ,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.m_card_corner_radius)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_per_person_def_elevation)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.m_default_margin))
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.title_total_per_person),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleMedium
            )

            val total = "%.2f".format(totalPerPerson.value)
            Text(
                text = "$ $total",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}