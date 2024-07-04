package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.ui.theme.EXTRA_LARGE_32_DP

@Composable
fun CallToActionButton(
    modifier: Modifier = Modifier,
    text: String,
    validInput: Boolean = false,
    onClickListener: () -> Unit
) {

    Button(
        onClick = onClickListener,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(EXTRA_LARGE_32_DP),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        enabled = validInput
    ) {
        Text(
            text = text.uppercase(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            
        )
    }
}

@Preview
@Composable
fun CallToActionButtonPreview() {
    CallToActionButton(text = "log in") {}
}
