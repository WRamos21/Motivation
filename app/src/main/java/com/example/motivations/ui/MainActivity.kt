package com.example.motivations.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivations.infra.MotivationConstants
import com.example.motivations.R
import com.example.motivations.data.Mock
import com.example.motivations.infra.SecurityPreferences
import com.example.motivations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    /* [3.1] Adcionando evento de click ao vetores
    Criamos uma função category para identificar qual é o vetor que foi clicado
    all = 1 | happy = 2 | sunny = 3, todos esses valores são costantes no MotivationCostants
     */
    private var categoryId = MotivationConstants.FILTER.ALL

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
        handleFilter(R.id.image_all)
        handleNextPhrase()

        //events
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)

    }

    /* [3.0] Adcionando evento de click ao vetores
    Não quero que o onClick faça nenhum tratamento de lógica, desta forma quando algum dos 3 botões
    forem clicados, onCiick chama handleFilter
    {continua em [3.1] na função handleFilter}
     */
    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()

        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun newPhrase() {
    }


    /* [2.4] Guardando dados
    Criamos a funçãp handle e intanciamos dentro de onCreate para ser exibida quando a activityMain
    aparecer. Para exibir o nome do usuario na textUserName, intanciamos Security e usamos o metodo
    getSting implementado com key USER_NAME que está na classe de constantes (MotivationConstants)
    {continua em [2.5] UserActivity}
    */
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    /* [3.0] Adcionando evento de click ao vetores}
    para adicionar a cor ao image quando for selecionado utilizamos setColorFilter,
    mas se apenas usarmos setColorFilter(R.color.white) a cor não é expressa da forma correta,
    por isso usamos ContextCompat.
    ContextCompat.getColor é um metodo do Android utilizado para obter uma cor de recursos (R.color)
    de forma compatível com diferentes versões do Android. Ele está na classe ContextCompat,
    que faz parte da biblioteca AndroidX.
    {continuar em [3.1] Acima}
     */
    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }

            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }

            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }
}