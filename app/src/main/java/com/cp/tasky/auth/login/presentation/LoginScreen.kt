package com.cp.tasky.auth.login.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cp.tasky.R
import com.cp.tasky.ui.sharecomponent.CallToActionButton
import com.cp.tasky.ui.sharecomponent.PasswordTextField
import com.cp.tasky.ui.sharecomponent.PostClickableText
import com.cp.tasky.ui.sharecomponent.UserInputTextField
import com.cp.tasky.ui.theme.EXTRA_LARGE_32_DP
import com.cp.tasky.ui.theme.LARGE_16_DP
import com.cp.tasky.ui.theme.MAJOR_EXTRA_LARGE_64_DP
import com.cp.tasky.ui.theme.MINOR_EXTRA_LARGE_48_DP

@Composable
fun LoginScreenRoot(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    LoginScreen(
        modifier = modifier,
        viewState = loginViewModel.loginScreenState,
        onEvents = loginViewModel::onEvents
    )
}


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewState: LoginScreenState,
    onEvents: (LoginScreenEvents) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                start = EXTRA_LARGE_32_DP,
                end = EXTRA_LARGE_32_DP,
                top = MAJOR_EXTRA_LARGE_64_DP,
                bottom = EXTRA_LARGE_32_DP
            ),
            text = "Welcome back!",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            contentColor = Color.White,
            shape = RoundedCornerShape(topStart = EXTRA_LARGE_32_DP, topEnd = EXTRA_LARGE_32_DP)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserInputTextField(
                    modifier = Modifier
                        .padding(
                            top = MAJOR_EXTRA_LARGE_64_DP,
                            start = LARGE_16_DP,
                            end = LARGE_16_DP
                        )
                        .height(MAJOR_EXTRA_LARGE_64_DP),
                    hint = stringResource(R.string.email_address),
                    trailingIcon = Icons.Default.Check,
                    iconContentDescription = stringResource(
                        R.string.valid,
                    ),
                    currentUserInput = viewState.email,
                    showTrailingIcon = viewState.isValidEmail,
                    onTextChange = { email ->
                        onEvents(
                            LoginScreenEvents.SetEmail(email)
                        )
                    })

                PasswordTextField(
                    modifier = Modifier
                        .padding(
                            top = LARGE_16_DP,
                            start = LARGE_16_DP,
                            end = LARGE_16_DP
                        )
                        .height(MAJOR_EXTRA_LARGE_64_DP),
                    password = viewState.password,
                    shouldHidePassword = viewState.shouldHidePassword,
                    passwordIconClick = { negateVisibility ->
                        onEvents(
                            LoginScreenEvents.SetHidePassword(negateVisibility)
                        )
                    },
                    onTextChange = { password ->
                        onEvents(
                            LoginScreenEvents.SetPassword(password)
                        )
                    })

                CallToActionButton(
                    modifier = Modifier
                        .padding(
                            top = LARGE_16_DP,
                            start = LARGE_16_DP,
                            end = LARGE_16_DP
                        )
                        .height(MINOR_EXTRA_LARGE_48_DP),
                    text = stringResource(R.string.log_in),
                    validInput = viewState.isValidEmail && viewState.isValidPassword
                ) {
                    onEvents(
                        LoginScreenEvents.LoginUser(
                            email = viewState.email, password = viewState.password
                        )
                    )
                }

                Spacer(modifier = Modifier.weight(.1f))

                PostClickableText(
                    modifier = Modifier
                        .padding(
                            bottom = MAJOR_EXTRA_LARGE_64_DP
                        ),
                    text = "don't have an account? sign up",
                    clickableText = "sign up"
                ) {
                    Log.d("LoginScreen", "Navigate to sign-up screen")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        viewState = LoginScreenState(),
        onEvents = { LoginScreenEvents.SetEmail("") }
    )
}
