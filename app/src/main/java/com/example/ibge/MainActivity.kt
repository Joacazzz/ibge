package com.example.ibge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ibge.ui.login.LoginScreen
import com.example.ibge.ui.register.RegisterScreen
import com.example.ibge.ui.theme.IBGETheme

sealed class AppScreen {
    object Login : AppScreen()
    object Register : AppScreen()
    object Home : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IBGETheme {
                var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Login) }

                when (currentScreen) {
                    is AppScreen.Login -> {
                        LoginScreen(
                            onLoginSuccess = { currentScreen = AppScreen.Home },
                            onNavigateToRegister = { currentScreen = AppScreen.Register }
                        )
                    }
                    is AppScreen.Register -> {
                        RegisterScreen(
                            onRegisterSuccess = { currentScreen = AppScreen.Login },
                            onBackToLogin = { currentScreen = AppScreen.Login }
                        )
                    }
                    is AppScreen.Home -> {
                        Scaffold(
                            topBar = {
                                @OptIn(ExperimentalMaterial3Api::class)
                                TopAppBar(title = { Text("Home - IBGE") })
                            }
                        ) { paddingValues ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "Bem-vindo ao sistema do IBGE!",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(onClick = { currentScreen = AppScreen.Login }) {
                                        Text("Sair (Logout)")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
