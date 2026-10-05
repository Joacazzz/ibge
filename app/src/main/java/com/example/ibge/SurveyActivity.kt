package com.example.ibge

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibge.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitVote.setOnClickListener {
            val selectedId = binding.radioGroupPresidents.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Selecione um candidato.", Toast.LENGTH_SHORT).show()
            } else {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val candidate = selectedRadioButton.text.toString()

                val intent = Intent(this, ReasonActivity::class.java)
                intent.putExtra("CANDIDATE", candidate)
                startActivity(intent)
                finish()
            }
        }
    }
}
