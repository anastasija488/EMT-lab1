package mk.ukim.finki.emtlab.service.Impl;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dtos.CountryDto;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplCountryService implements CountryService {
    private final CountryRepository countryRepository;

    public ImplCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    @Override
    public Country save(CountryDto country) {
        Country newCountry = new Country();

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }

    @Override
    public Country update(Long id, CountryDto country) {
        Country newCountry = countryRepository.findById(id).orElse(null);

        if (newCountry == null) {
            return null;
        }

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }


    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}