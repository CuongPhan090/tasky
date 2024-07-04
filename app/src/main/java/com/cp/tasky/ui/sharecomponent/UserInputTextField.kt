package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.ui.theme.MEDIUM_8_DP

@Composable
fun UserInputTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    trailingIcon: ImageVector,
    iconContentDescription: String,
    currentUserInput: String,
    showTrailingIcon: Boolean,
    onTextChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(MEDIUM_8_DP),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Remove underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
        ),
        trailingIcon = {
            if (showTrailingIcon)
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = iconContentDescription,
                    tint = Color.Green.copy(green = 0.7f)
                )
        },
        value = currentUserInput,
        onValueChange = { newText ->
             onTextChange(newText)
        },
        label = {
            Text(hint)
        },
        singleLine = true,
        maxLines = 1,
    )
}

@Preview
@Composable
fun UserInputTextFieldPreview() {
    UserInputTextField(
        hint = "Email address",
        trailingIcon = Icons.Default.Check,
        iconContentDescription = "Valid",
        currentUserInput = "sample@gmail.com",
        showTrailingIcon = false,
        onTextChange = {},
    )
}
