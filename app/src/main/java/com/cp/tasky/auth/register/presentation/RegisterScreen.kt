package com.cp.tasky.auth.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cp.tasky.R
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.ui.sharecomponent.CallToActionButton
import com.cp.tasky.ui.sharecomponent.PasswordTextField
import com.cp.tasky.ui.sharecomponent.UserInputTextField
import com.cp.tasky.ui.theme.EXTRA_LARGE_32_DP
import com.cp.tasky.ui.theme.LARGE_16_DP
import com.cp.tasky.ui.theme.MAJOR_EXTRA_LARGE_64_DP
import com.cp.tasky.ui.theme.MINOR_EXTRA_LARGE_48_DP

@Composable
fun RegisterScreenRoot(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    RegisterScreen(
        modifier = modifier,
        viewState = registerViewModel.registerScreenState,
        networkState = registerViewModel.registerScreenNetworkState.collectAsStateWithLifecycle().value,
        onEvents = registerViewModel::onEvents
    )
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewState: RegisterViewState,
    networkState: Result<Unit, Error>, // TODO: Handle network state when it is loading/ success/ error
    onEvents: (RegisterScreenEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = EXTRA_LARGE_32_DP,
                    end = EXTRA_LARGE_32_DP,
                    top = MAJOR_EXTRA_LARGE_64_DP,
                    bottom = EXTRA_LARGE_32_DP
                ),
            text = stringResource(R.string.create_your_account),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
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
                    trailingIcon = Icons.Default.Check,
                    iconContentDescription = stringResource(id = R.string.valid),
                    currentUserInput = viewState.userName,
                    showTrailingIcon = viewState.isValidUserName,
                    hint = stringResource(R.string.name),
                    onTextChange = { userName ->
                        onEvents(
                            RegisterScreenEvent.SetUserName(userName)
                        )
                    }
                )

                UserInputTextField(
                    modifier = Modifier
                        .padding(
                            top = LARGE_16_DP,
                            start = LARGE_16_DP,
                            end = LARGE_16_DP
                        )
                        .height(MAJOR_EXTRA_LARGE_64_DP),
                    trailingIcon = Icons.Default.Check,
                    iconContentDescription = stringResource(id = R.string.valid),
                    currentUserInput = viewState.email,
                    showTrailingIcon = viewState.isValidEmail,
                    hint = stringResource(R.string.email_address),
                    onTextChange = { userName ->
                        onEvents(
                            RegisterScreenEvent.SetEmail(userName)
                        )
                    }
                )

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
                            RegisterScreenEvent.SetHidePassword(negateVisibility)
                        )
                    },
                    onTextChange = { password ->
                        onEvents(
                            RegisterScreenEvent.SetPassword(password)
                        )
                    }
                )

                CallToActionButton(
                    modifier = Modifier
                        .padding(
                            top = EXTRA_LARGE_32_DP,
                            start = LARGE_16_DP,
                            end = LARGE_16_DP
                        )
                        .height(MINOR_EXTRA_LARGE_48_DP),
                    text = stringResource(R.string.get_started),
                    validInput = viewState.isValidUserName && viewState.isValidEmail && viewState.isValidPassword
                ) {
                    onEvents(
                        RegisterScreenEvent.RegisterUser(
                            userName = viewState.userName,
                            email = viewState.email,
                            password = viewState.password
                        )
                    )
                }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                    FilledIconButton(
                        modifier = Modifier
                            .padding(
                                start = LARGE_16_DP,
                                bottom = EXTRA_LARGE_32_DP
                            )
                            .size(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        onClick = {
                            //TODO: Go back to Login Screen
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.go_back)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(viewState = RegisterViewState(), networkState = Result.Idle) {}
}