package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}