package ca.josuelubaki.countryapp.domain.usecases

import ca.josuelubaki.countryapp.data.mapper.toDomain
import ca.josuelubaki.countryapp.data.repository.CountryRepositoryImpl
import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.domain.repository.CountryRepository

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

class GetCountryDetailsByCodeUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(code : String) : Country? {
        return repository.getCountryDetailsByCode(code).execute().data?.country?.toDomain()
    }
}
