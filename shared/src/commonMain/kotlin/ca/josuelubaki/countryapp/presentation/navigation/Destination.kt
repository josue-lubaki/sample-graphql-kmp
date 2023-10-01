package ca.josuelubaki.countryapp.presentation.navigation

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
    val argsName: String
        get() = ""
}

object Continents: Destination {
    override val title: String
        get() = "Continents List"

    override val route: String
        get() = "continents"

    override val routeWithArgs: String
        get() = route
}

object Continent: Destination {
    override val title: String
        get() = "Continent details"

    override val route: String
        get() = "continent_details"

    override val argsName: String
        get() = "id"

    override val routeWithArgs: String
        get() = "$route/{$argsName}"
}

object Country: Destination {
    override val title: String
        get() = "Country details"

    override val route: String
        get() = "country_details"

    override val argsName: String
        get() = "code"

    override val routeWithArgs: String
        get() = "$route/{$argsName}"
}