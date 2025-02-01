package com.example.motivations

import android.content.Context
import android.content.SharedPreferences

/*[2.0] Guardando dados
SharedPreferences guarda dados para serem acessados rapidamente
se quiser instanciar usamos dentro de uma activity usamos:
val a: SharedPreferences = applicationContext.getSharedPreferences("Nome da Shared", Context.MODE_PRIVATE)
Context.MODE_PRIVATE deixa os dados privados apenas para a aplicação.
Porém a classe não tem contexto, portanto é necessário receber o contexto de uma Activity
pelo seu parametro.
{Continua em [2.1] abaixo}
 */

class SecurityPreferences(context: Context) {
    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)


    /*[2.1] Guardando dados
    o metodo edit().putString() modfica SharedPreferences (Security) com uma String,
    put é um metodo que recebe um chave e o dado a ser adicionado. apply confirma as alterações
    {continua em [2.2] abaixo}
     */
    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    /*[2.2] Guardando dados
    getString procura um String a depender da key dada, e caso não encontre o valor definimos
    para retornar "".
    Mas getString também pode retornar nulo (por erro nos dados), por isso utilizamos elvis "?:" para retornar "" quando
    isso acontecer
    {continuar em [2.3] na UserActivity}
     */
    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
    }
}