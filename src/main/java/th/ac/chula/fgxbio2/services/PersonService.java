package th.ac.chula.fgxbio2.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Country;
import th.ac.chula.fgxbio2.models.tables.Person;
import th.ac.chula.fgxbio2.models.tables.Province;
import th.ac.chula.fgxbio2.models.tables.Race;
import th.ac.chula.fgxbio2.models.tables.Region;
import th.ac.chula.fgxbio2.payload.request.PersonCustom;
import th.ac.chula.fgxbio2.repository.tables.CountryRepository;
import th.ac.chula.fgxbio2.repository.tables.PersonRepository;
import th.ac.chula.fgxbio2.repository.tables.ProvinceRepository;
import th.ac.chula.fgxbio2.repository.tables.RaceRepository;
import th.ac.chula.fgxbio2.repository.tables.RegionRepository;

@Service
public class PersonService {

	@Autowired
	public PersonRepository personRepository;

	@Autowired
	public CountryRepository countryRepository;

	@Autowired
	public RegionRepository regionRepository;

	@Autowired
	public ProvinceRepository provinceRepository;

	@Autowired
	public RaceRepository raceRepository;

	@Transactional
	public String updatePerson(Integer id, PersonCustom body) {
		int raceId = body.getRace_id();
		int countryId = body.getCountry_id();
		int regionId = body.getRegion_id();
		int provinceId = body.getProvince_id();
		System.out.println(raceRepository.existsById(raceId));
		System.out.println(countryRepository.existsById(countryId));
		System.out.println(regionRepository.existsById(regionId));
		System.out.println(provinceRepository.existsById(provinceId));

		if (raceRepository.existsById(raceId) && countryRepository.existsById(countryId)
				&& regionRepository.existsById(regionId) && provinceRepository.existsById(provinceId)) {

			
			Person person = personRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Person not found :: " + id));

			person.setAge(body.getAge());
			person.setFirstname(body.getFirstname());
			person.setLastname(body.getLastname());
			person.setGender(body.getGender());

			Country country = countryRepository.findById(countryId)
					.orElseThrow(() -> new ResourceNotFoundException("Country not found :: " + countryId));
			Region region = regionRepository.findById(regionId)
					.orElseThrow(() -> new ResourceNotFoundException("Region not found :: " + regionId));
			Province province = provinceRepository.findById(provinceId)
					.orElseThrow(() -> new ResourceNotFoundException("Province not found :: " + provinceId));
			Race race = raceRepository.findById(raceId)
					.orElseThrow(() -> new ResourceNotFoundException("Race not found :: " + raceId));

			person.setCountry(country);
			person.setRegion(region);
			person.setProvince(province);
			person.setRace(race);

			personRepository.save(person);
			
			return "OK";
		}

		return null;
	}

}
