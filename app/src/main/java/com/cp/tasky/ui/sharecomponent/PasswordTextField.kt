package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.R
import com.cp.tasky.ui.theme.MEDIUM_8_DP

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    shouldHidePassword: Boolean,
    passwordIconClick: (Boolean) -> Unit,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = password,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        shape = RoundedCornerShape(MEDIUM_8_DP),
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text("Password")
        },
        trailingIcon = {
            Icon(
                painter = if (shouldHidePassword) painterResource(id = R.drawable.visitibility_off) else painterResource(
                    id = R.drawable.visitability_on
                ),
                contentDescription = if (shouldHidePassword) "Show password" else "Hide password",
                modifier = Modifier.clickable {
                    passwordIconClick(!shouldHidePassword)
                })
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Remove underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (shouldHidePassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}


@Preview
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField(password = "Password123", shouldHidePassword = true, passwordIconClick = {}) {}
}
