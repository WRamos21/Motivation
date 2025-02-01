package com.example.motivations.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivations.infra.MotivationConstants
import com.example.motivations.R
import com.example.motivations.infra.SecurityPreferences
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

        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    /* [1.0] Navegação entre Activitys
    salva valor em name e se for vazio mostra na tela com Toast
    Intent carrega MainActivity (a proxima que será exibida), mas start activity é o que leva a ela.
    finish impede o usuario de voltar a tela UserActivity
    [2.0] Guardando dados {Continua na classe SecurityPreferences}
    [2.3] Guardando dados
    agora eu intancio a classe SecurityPreferences passando o contexto desta activity, e chamo o metodo
    store, armazenado name com key USER_NAME
    {Continua em [2.4] na MainActivity}
     */
    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

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

    /* [2.5] Guardando dados
    verifyUserName ira verificar se o usuario já tem nome cadastrado para já envia-lo para a
    Activity Main
     */
    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}