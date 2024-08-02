package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.R
import com.cp.tasky.ui.theme.EXTRA_SMALL_2_DP
import com.cp.tasky.ui.theme.LARGE_16_DP

@Composable
fun TimeNeedle(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        HorizontalDivider(
            thickness = EXTRA_SMALL_2_DP,
            color = Color.Black,
            modifier = Modifier.padding(start = EXTRA_SMALL_2_DP)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = "",
            modifier = Modifier.size(
                LARGE_16_DP
            )
        )
    }
}

@Preview
@Composable
private fun TimeNeedlePreview() {
    TimeNeedle()
}