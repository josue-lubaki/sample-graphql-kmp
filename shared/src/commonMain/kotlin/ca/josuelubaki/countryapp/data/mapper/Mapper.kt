package ca.josuelubaki.countryapp.data.mapper

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.common.ContinentsQuery
import ca.josuelubaki.countryapp.common.CountryQuery
import ca.josuelubaki.countryapp.domain.models.Continent
import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.domain.models.Language
import ca.josuelubaki.countryapp.domain.models.State

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

fun List<ContinentsQuery.Continent>?.toDomain() : List<Continent> {
    return this?.map { it.toDomain() } ?: emptyList()
}

private fun ContinentsQuery.Continent.toDomain() : Continent {
    return Continent(
        code = code,
        name = name
    )
}

fun ContinentQuery.Continent.toDomain(): Continent {
    return Continent(
        code = code,
        name = name,
        countries = countries.map { it.toDomain() }
    )
}

private fun ContinentQuery.Country.toDomain() : Country {
    return Country(
        code = code,
        name = name,
        emoji = emoji
    )
}

fun CountryQuery.Country.toDomain(): Country {
    return Country(
        code = code,
        name = name,
        emoji = emoji,
        continent = continent.toDomain(),
        currency = currency,
        native = native,
        phone = phone,
        states = states.map { it.toDomain() },
        languages = languages.map { it.toDomain() }
    )
}

private fun CountryQuery.Continent.toDomain() : Continent {
    return Continent(
        code = code,
        name = name
    )
}

private fun CountryQuery.State.toDomain() : State {
    return State(
        code = code,
        name = name
    )
}

private fun CountryQuery.Language.toDomain() : Language {
    return Language(
        code = code,
        name = name,
        rtl = rtl,
        native = native
    )
}