package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.ui.theme.LARGE_16_DP
import com.cp.tasky.ui.theme.MEDIUM_8_DP
import com.cp.tasky.ui.theme.MEDIUM_LARGE_12_DP
import com.cp.tasky.ui.theme.SMALL_4_DP

typealias dayOfWeek = String
typealias dayOfMonth = String

@Composable
fun Next6DaysSelector(
    modifier: Modifier = Modifier,
    nextSixDays: Map<dayOfMonth, dayOfWeek>,
    currentlySelectedDate: String,
    onDateSelected: (String) -> Unit,
) {

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        nextSixDays.forEach {
            PairOfDay(
                dateOfWeek = it.value,
                dateOfMonth = it.key,
                currentlySelectedDate = it.key == currentlySelectedDate
            ) { date ->
                onDateSelected(date)
            }
        }
    }
}

@Composable
fun PairOfDay(
    modifier: Modifier = Modifier,
    dateOfWeek: String,
    dateOfMonth: String,
    currentlySelectedDate: Boolean = false,
    onDateClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .clickable { onDateClicked(dateOfMonth) }
            .background(
                color = if (currentlySelectedDate) Color.Yellow else Color.Transparent,
                shape = RoundedCornerShape(LARGE_16_DP)
            )
            .padding(vertical = MEDIUM_8_DP, horizontal = MEDIUM_LARGE_12_DP),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = dateOfWeek)
        Spacer(modifier = Modifier.padding(SMALL_4_DP))
        Text(text = dateOfMonth)
    }
}

@Preview
@Composable
private fun PairOfDayPreview() {
    PairOfDay(dateOfWeek = "S", dateOfMonth = "5") {}
}

@Preview
@Composable
private fun Next6DaysSelectorPreview() {
    Next6DaysSelector(
        nextSixDays = mapOf(
            "5" to "S",
            "6" to "M",
            "7" to "T",
            "8" to "W",
            "9" to "T",
            "10" to "F"
        ),
        currentlySelectedDate = "6",
        onDateSelected = {}
    )
}
