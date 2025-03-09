package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dtos.CountryDto;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CountryController {
    
    private final CountryService countryService;
    
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    
    @GetMapping("/api/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
    
    @GetMapping("/api/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        if (country == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }
    
    @PostMapping("/api/countries/create")
    public ResponseEntity<Country> createCountry(@RequestBody CountryDto countryDto) {
        Country country = countryService.save(countryDto);
        return ResponseEntity.ok(country);
    }
    
    @PutMapping("/api/countries/{id}/update")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        Country country = countryService.update(id, countryDto);
        if (country == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }
    
    @DeleteMapping("/api/countries/{id}/delete")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
