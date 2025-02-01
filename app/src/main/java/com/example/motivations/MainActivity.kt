package com.example.motivations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Esconde barra de support
        supportActionBar?.hide()

        handleUserName()

        //events
        binding.buttonNewPhrase.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            var s = "a"
        }
    }

    /* [2.4] Guardando dados
    Criamos a funçãp handle e intanciamos dentro de onCreate para ser exibida quando a activityMain
    aparecer. Para exibir o nome do usuario na textUserName, intanciamos Security e usamos o metodo
    getSting implementado com key USER_NAME
    */
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString("USER_NAME")
        binding.textUserName.text = "Olá, $name!"
    }

}