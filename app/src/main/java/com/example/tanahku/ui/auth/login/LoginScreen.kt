package com.example.tanahku.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.component.Form
import com.example.tanahku.ui.component.TanahKuButton
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.utils.UiState


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isButtonClicked by remember { mutableStateOf(false) }
            var openDialog by remember { mutableStateOf(false)  }

            Header()
            LogoAndTitle(modifier = Modifier.offset(y = (-20).dp))
            Form(titleId = R.string.email, onValueChanged = { value ->
                email = value
            })
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Form(titleId = R.string.password, onValueChanged = { value ->
                password = value
            })
            Spacer(modifier = Modifier.padding(vertical = 16.dp))


            val uiState by viewModel.uiStateLiveData.observeAsState(initial = UiState.Loading)

            // Button and SignUpText
            SignInButtonAndSignUpText(navController, onClick = {
                isButtonClicked = true
                openDialog = true
                if (email.isEmpty() || password.isEmpty()) {
                    // Set error messages for invalid input
                    if (email.isEmpty()) {
                        // Handle email validation
                    }
                    if (password.isEmpty()) {
                        // Handle password validation
                    }
                } else {
                    viewModel.loginUser(email, password)

                }

            })

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Footer()

            if (isButtonClicked && uiState is UiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(75.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = (-350).dp)
                )
            } else if (uiState is UiState.Success) {
                val loginResponse = (uiState as UiState.Success).data
                navController.navigate(Screen.Home.route)
            } else if (openDialog && uiState is UiState.Error) {
                AlertDialog(
                    onDismissRequest = {openDialog = false},
                    title = { Text((uiState as UiState.Error).errorMessage) },
                    text = { Text("Please check your email and password and try again.") },
                    confirmButton = {
                        Button(
                            onClick = {openDialog = false},
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        ) {
                            Text("OK")
                        }
                    }
                )
            }


        }
    }
}


@Composable
fun LogoAndTitle(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Group 8",
            modifier = Modifier
                .requiredWidth(width = 150.dp)
                .requiredHeight(height = 150.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TanahKu",
                color = Color(0xff2e2836),
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
            Text(
                text = "Soil Classification and\nCrop Recommendation App",
                color = Color(0xff2e2836),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .requiredWidth(width = 189.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
    }
}


@Composable
fun SignInButtonAndSignUpText(navController: NavHostController, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        TanahKuButton(title = "Masuk", onClick = onClick)
        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xff2e2836),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Belum punya akun?")
                }
            },
            onClick = {
                navController.navigate("register")
            }
        )
    }
}


@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.header_background),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .fillMaxSize()
        )
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.footer_background),
            contentDescription = "Footer",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(height = 110.dp)
                .fillMaxWidth()
        )
    }
}
