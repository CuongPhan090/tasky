package com.cp.tasky.auth.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cp.tasky.R
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.core.presentation.util.getErrorMessage
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
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterScreenClick: () -> Unit
) {
    LoginScreen(
        modifier = modifier,
        viewState = loginViewModel.screenState,
        onEvents = loginViewModel::onEvents,
        networkState = loginViewModel.networkResponse.collectAsStateWithLifecycle(initialValue = null).value,
        onLoginSuccess = onLoginSuccess,
        onRegisterScreenClick = onRegisterScreenClick
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    viewState: LoginScreenState,
    onEvents: (LoginScreenEvent) -> Unit,
    networkState: LoginEvent?,
    onLoginSuccess: () -> Unit,
    onRegisterScreenClick: () -> Unit
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

                if (viewState.isLoading) {
                    Dialog(onDismissRequest = {}) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(100.dp))
                        }
                    }
                }

                if (networkState is LoginEvent.LoginSuccess) {
                    onLoginSuccess()
                }

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
                            LoginScreenEvent.SetEmail(email)
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
                            LoginScreenEvent.SetHidePassword(negateVisibility)
                        )
                    },
                    onTextChange = { password ->
                        onEvents(
                            LoginScreenEvent.SetPassword(password)
                        )
                    })

                if (viewState.errorMessage != null) {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = LARGE_16_DP,
                                start = LARGE_16_DP,
                                end = LARGE_16_DP
                            )
                            .align(Alignment.Start),
                        text = (viewState.errorMessage as? DataError)?.getErrorMessage()
                            ?: stringResource(id = R.string.unknown_error),
                        color = Color.Red,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

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
                        LoginScreenEvent.LoginUser(
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
                    text = stringResource(R.string.don_t_have_an_account_sign_up),
                    clickableText = stringResource(R.string.sign_up)
                ) {
                    onRegisterScreenClick()
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
        onEvents = { LoginScreenEvent.SetEmail("") },
        networkState = null,
        onLoginSuccess = {},
        onRegisterScreenClick = {}
    )
}
