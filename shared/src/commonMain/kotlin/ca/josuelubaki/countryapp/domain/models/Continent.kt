package ca.josuelubaki.countryapp.domain.models

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

data class Continent (
    val code: String,
    val name: String,
    val countries: List<Country>? = null
)
