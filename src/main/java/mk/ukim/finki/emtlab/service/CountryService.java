package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dtos.CountryDto;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Long country);

    Country save(CountryDto country);

    Country update(Long id, CountryDto country);

    void deleteCountry(Long id);
}