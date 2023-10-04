package ca.josuelubaki.countryapp

import androidx.compose.runtime.Composable
import ca.josuelubaki.countryapp.presentation.navigation.Continent
import ca.josuelubaki.countryapp.presentation.navigation.Continents
import ca.josuelubaki.countryapp.presentation.navigation.Country
import ca.josuelubaki.countryapp.presentation.countries.ContinentScreen
import ca.josuelubaki.countryapp.presentation.countries.CountriesViewModel
import ca.josuelubaki.countryapp.presentation.continents.ContinentsScreen
import ca.josuelubaki.countryapp.presentation.continents.ContinentsViewModel
import ca.josuelubaki.countryapp.presentation.country.CountryScreen
import ca.josuelubaki.countryapp.presentation.country.CountryViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@Composable
fun CountryAppSetup() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = Continents.route,
    ) {
        scene(
            route = Continents.route,
            navTransition = NavTransition(),
        ) {
            val vm = koinViewModel(ContinentsViewModel::class)
            ContinentsScreen(vm) {
                navigator.navigate("${Continent.route}/$it")
            }
        }

        scene(route = Continent.routeWithArgs) { backStackEntry ->
            val vm = koinViewModel(CountriesViewModel::class)
            val code: String? = backStackEntry.path<String>(Continent.argsName)
            ContinentScreen(
                code = code,
                viewModel = vm,
                onCountryClick = {
                    navigator.navigate("${Country.route}/$it")
                },
                onBackPress = {
                    navigator.goBack()
                }
            )
        }

        scene(
            route = Country.routeWithArgs,
            navTransition = NavTransition(),
        ) { backStackEntry ->
            val vm = koinViewModel(CountryViewModel::class)
            val code: String? = backStackEntry.path<String>(Country.argsName)
            CountryScreen(
                code = code,
                viewModel = vm,
                onBackPress = {
                    navigator.goBack()
                }
            )
        }
    }
}