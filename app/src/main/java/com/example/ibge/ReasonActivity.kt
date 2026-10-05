package com.example.ibge

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibge.databinding.ActivityReasonBinding

class ReasonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReasonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val candidate = intent.getStringExtra("CANDIDATE") ?: "não informado"

        binding.btnFinishSurvey.setOnClickListener {
            val selectedId = binding.radioGroupReasons.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Selecione o motivo do seu voto.", Toast.LENGTH_SHORT).show()
            } else {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val reason = selectedRadioButton.text.toString()
                val details = binding.etReasonDetails.text?.toString()?.trim() ?: ""

                val message = if (details.isNotEmpty()) {
                    "Voto registrado em $candidate por $reason ($details)! Obrigado."
                } else {
                    "Voto registrado em $candidate por $reason! Obrigado."
                }

                Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                // Navigate back to Home
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish()
            }
        }
    }
}
