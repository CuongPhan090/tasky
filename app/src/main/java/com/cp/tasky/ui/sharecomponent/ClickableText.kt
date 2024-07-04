package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PostClickableText(
    modifier: Modifier = Modifier,
    text: String,
    clickableText: String,
    onTextClick: () -> Unit
) {
    val nonClickableString = text.substring(0, text.indexOf(clickableText))

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray
            )
        ) {
            append(nonClickableString)
        }

        pushStringAnnotation(
            tag = "Clickable",
            annotation = "sign-up screen url"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue.copy(blue = 0.4f)
            )
        ) {
            append(clickableText)
        }
        pop()
    }

    ClickableText(
        modifier = modifier,
        text = annotatedString.toUpperCase(),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "Clickable", start = offset, end = offset)
                .firstOrNull()?.let {
                    onTextClick()
                }
        }
    )
}

@Preview
@Composable
fun ClickableTextPreview() {
    val text = "don't have an account? sign up"
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        PostClickableText(text = text, clickableText = "sign up") {}
    }
}
