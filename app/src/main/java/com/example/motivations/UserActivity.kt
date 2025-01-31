package com.example.motivations

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivations.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    /* [1.0] Navegação entre Activitys
    salva valor em name e se for vazio mostra na tela com Toast
    Intent carrega MainActivity mas start activity é o que leva a ela
    finish impede o usuario de voltar a tela
     */
    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                R.string.validation_mandatory_name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}