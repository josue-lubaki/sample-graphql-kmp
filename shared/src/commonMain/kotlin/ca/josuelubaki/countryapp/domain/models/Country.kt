package ca.josuelubaki.countryapp.domain.models

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

data class Country(
    val name: String,
    val code: String,
    val emoji: String,
    val continent : Continent? = null,
    val currency: String? = null,
    val native: String? = null,
    val phone: String? = null,
    val states: List<State>? = null,
    val languages: List<Language>? = null,
)
