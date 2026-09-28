package com.example.ibge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ibge.ui.login.LoginScreen
import com.example.ibge.ui.register.RegisterScreen
import com.example.ibge.ui.survey.SurveyScreen
import com.example.ibge.ui.theme.IBGETheme

sealed class AppScreen {
    object Login : AppScreen()
    object Register : AppScreen()
    object Home : AppScreen()
    object Survey : AppScreen()
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
                                    .padding(paddingValues)
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Bem-vindo ao sistema do IBGE!",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Spacer(modifier = Modifier.height(32.dp))

                                    // Button / Card to answer election survey with icon
                                    ElevatedCard(
                                        onClick = { currentScreen = AppScreen.Survey },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(20.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Poll,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(36.dp)
                                            )
                                            Column {
                                                Text(
                                                    text = "Pesquisa de Eleições",
                                                    style = MaterialTheme.typography.titleMedium
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = "Responder pesquisa de eleições presidenciais",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(24.dp))

                                    OutlinedButton(
                                        onClick = { currentScreen = AppScreen.Login },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Sair (Logout)")
                                    }
                                }
                            }
                        }
                    }
                    is AppScreen.Survey -> {
                        SurveyScreen {
                            currentScreen = AppScreen.Home
                        }
                    }
                }
            }
        }
    }
}
