package th.ac.chula.fgxbio2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Country;
import th.ac.chula.fgxbio2.repository.tables.CountryRepository;

@Service
public class CountrySevice {
	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		List<Country> countriesList = countryRepository.findAll();
		return countriesList;
	}
	
	
}
