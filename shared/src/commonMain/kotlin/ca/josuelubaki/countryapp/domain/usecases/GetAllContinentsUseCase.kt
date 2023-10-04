package ca.josuelubaki.countryapp.domain.usecases

import ca.josuelubaki.countryapp.data.mapper.toDomain
import ca.josuelubaki.countryapp.domain.models.Continent
import ca.josuelubaki.countryapp.domain.repository.ContinentsRepository

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

class GetAllContinentsUseCase(
    private val repository: ContinentsRepository
) {
    suspend operator fun invoke() : List<Continent>? {
        return repository.getAllContinents().execute().data?.continents?.toDomain()
    }
}