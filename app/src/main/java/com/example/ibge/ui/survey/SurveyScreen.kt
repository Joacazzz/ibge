package com.example.ibge.ui.survey

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Poll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen(
    onBackToHome: () -> Unit
) {
    val candidateOptions = listOf(
        "Getúlio Vargas",
        "Fernando Henrique",
        "João Goulart",
        "Nilo Peçanha",
        "Juscelino Kubitschek",
        "Outros",
    )

    val reasonOptions = listOf(
        "Educação",
        "Saúde",
        "Saneamento básico",
        "Segurança pública",
        "Desenvolvimento econômico",
        "Tecnologia e Infraestrutura",
    )

    var currentStep by remember { mutableIntStateOf(1) }
    var selectedCandidate by remember { mutableStateOf<String?>(null) }
    var selectedReason by remember { mutableStateOf<String?>(null) }
    var reasonDetails by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pesquisa de Eleições") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (currentStep == 2) {
                                currentStep = 1
                            } else {
                                onBackToHome()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> {
                    Text(
                        text = "Pesquisa Presidencial",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Em qual destes presidentes você votaria?",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    candidateOptions.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (selectedCandidate == option),
                                    onClick = { selectedCandidate = option },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (selectedCandidate == option),
                                onClick = { selectedCandidate = option }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (selectedCandidate != null) {
                                currentStep = 2
                            }
                        },
                        enabled = selectedCandidate != null,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Próximo")
                    }
                }

                2 -> {
                    Text(
                        text = "Motivo do Voto",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Por que você votou em $selectedCandidate? Selecione a principal razão:",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    reasonOptions.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (selectedReason == option),
                                    onClick = { selectedReason = option },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 10.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (selectedReason == option),
                                onClick = { selectedReason = option }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = reasonDetails,
                        onValueChange = { reasonDetails = it },
                        label = { Text("Descreva com mais detalhes") },
                        placeholder = { Text("Escreva mais detalhes sobre o motivo do seu voto...") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (selectedReason != null) {
                                currentStep = 3
                            }
                        },
                        enabled = selectedReason != null,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Finalizar Pesquisa")
                    }
                }

                3 -> {
                    Icon(
                        imageVector = Icons.Default.Poll,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Voto registrado com sucesso!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Candidato: $selectedCandidate",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Motivo: $selectedReason",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    if (reasonDetails.isNotBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Detalhes: $reasonDetails",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = onBackToHome) {
                        Text("Voltar para Home")
                    }
                }
            }
        }
    }
}
