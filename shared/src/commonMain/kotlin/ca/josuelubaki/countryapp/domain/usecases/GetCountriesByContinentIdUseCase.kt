package ca.josuelubaki.countryapp.domain.usecases

import ca.josuelubaki.countryapp.data.mapper.toDomain
import ca.josuelubaki.countryapp.domain.models.Continent
import ca.josuelubaki.countryapp.domain.repository.ContinentRepository

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

class GetCountriesByContinentIdUseCase(
    private val repository: ContinentRepository
) {
    suspend operator fun invoke(id : String) : Continent? {
        return repository.getContinentById(id).execute().data?.continent?.toDomain()
    }
}
