package com.jdbarad.jaypalsinhbarad.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jdbarad.jaypalsinhbarad.R
import com.jdbarad.jaypalsinhbarad.showMessage

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToPart1: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onErrorMessage = ::showMessage
        viewModel.onNavigateToPart1 = onNavigateToPart1
    }

    LoginScreen(
        mail = state.email,
        password = state.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginCLick
    )
}

@Preview
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    mail: String = "",
    password: String = "",
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            painter = painterResource(R.drawable.ic_app_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(100.dp, 0.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (mainLayout, titleText, supportLayout) = createRefs()

            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(parent.top, margin = 28.dp)
                    start.linkTo(parent.start, margin = 28.dp)
                    end.linkTo(parent.end, margin = 28.dp)
                    bottom.linkTo(mainLayout.top, margin = 28.dp)
                }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .constrainAs(mainLayout) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = mail,
                    onValueChange = onEmailChange,
                    label = { Text(stringResource(R.string.e_mail)) },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text(stringResource(R.string.password)) },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp),
                    onClick = onLoginClick
                ) {
                    Text(stringResource(R.string.login))
                }
            }
            Row(
                Modifier.constrainAs(supportLayout) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.don_t_have_any_account))
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = {}
                ) {
                    Text(stringResource(R.string.sign_up))
                }
            }

        }
    }
}