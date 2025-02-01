package com.example.motivations.infra

/* Criando classe de constantes
O uso de object dentro da classe MotivationConstants serve para criar um namespace para as constantes,
 garantindo organização e acessibilidade global sem a necessidade de instanciar a classe.
 */
class MotivationConstants private constructor(){
    object KEY {
        const val USER_NAME = "USER_NAME"
    }
}