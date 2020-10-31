package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.models.tables.Country;
import th.ac.chula.fgxbio2.services.CountrySevice;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
	@Autowired
	private CountrySevice countryService;

	@GetMapping("/")
	public ResponseEntity<?> getAllCountires() {
		List<Country> countriesList = countryService.getAllCountries();
		return ResponseEntity.status(HttpStatus.OK).body(countriesList);
	}
}
