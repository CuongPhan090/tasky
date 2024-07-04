package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.cp.tasky.R
import com.cp.tasky.ui.theme.MEDIUM_8_DP


@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    // TODO: Move these two states to viewModel
    var password by remember { mutableStateOf("") }
    var hidePassword by remember { mutableStateOf(true) }

    TextField(
        value = password,
        onValueChange = {
            password = it
        },
        shape = RoundedCornerShape(MEDIUM_8_DP),
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text("Password")
        },
        trailingIcon = {
            Icon(
                painter = if (hidePassword) painterResource(id = R.drawable.visitibility_off) else painterResource(
                    id = R.drawable.visitability_on
                ),
                contentDescription = if (hidePassword) "Show password" else "Hide password",
                modifier = Modifier.clickable {
                    hidePassword = !hidePassword
                })
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Remove underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (hidePassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}


@Preview
@Composable
fun PasswordTextFieldPreview() {
    PasswordTextField()
}