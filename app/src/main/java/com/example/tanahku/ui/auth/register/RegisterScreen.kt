package com.example.tanahku.ui.auth.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tanahku.R
import com.example.tanahku.ViewModelFactory
import com.example.tanahku.di.Injection
import com.example.tanahku.ui.auth.login.Footer
import com.example.tanahku.ui.auth.login.Header
import com.example.tanahku.ui.auth.login.LogoAndTitle
import com.example.tanahku.ui.component.Form
import com.example.tanahku.ui.component.TanahKuButton
import com.example.tanahku.ui.navigation.Screen
import com.example.tanahku.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var openDialog by remember { mutableStateOf(false) }
        var isButtonClicked by remember { mutableStateOf(false) }

        Header()
        LogoAndTitle(modifier = Modifier.offset(y = (-50).dp))
        Form(titleId =  R.string.email, onValueChanged = {value ->
            email = value
        })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Form(titleId =  R.string.username, onValueChanged = { value ->
            username = value
        })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Form(titleId =  R.string.password, onValueChanged = { value ->
            password = value
        })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        RegisterButtonAndSignInText(navController, onClick = {
            isButtonClicked = true
            openDialog = true
            viewModel.postUser(username, email, password)
        })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Footer()
        val uiState by viewModel.uiStateLiveData.observeAsState(initial = UiState.Loading)
        if (isButtonClicked && uiState is UiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(75.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-300).dp)
            )
        } else if (uiState is UiState.Success) {
            val registerResponse = (uiState as UiState.Success).data
            navController.navigate(Screen.Login.route)
            showSuccessToast(message = registerResponse.status)
        } else if (openDialog && uiState is UiState.Error) {
            AlertDialog(
                onDismissRequest = {openDialog = false},
                title = { Text((uiState as UiState.Error).errorMessage) },
                text = { Text("Please check your field again") },
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

@Composable
private fun showSuccessToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun RegisterButtonAndSignInText(navHostController: NavHostController, onClick: () -> Unit ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TanahKuButton(title = "Register", onClick = onClick)
        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xff2e2836),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Sudah punya akun? Masuk")
                }
            },
            onClick = {
                navHostController.navigate("login")
            }
        )
    }
}

