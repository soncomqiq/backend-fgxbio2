package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.ForenseqRow;
import th.ac.chula.fgxbio2.datastucture.ForenseqSequenceRow;
import th.ac.chula.fgxbio2.models.tables.Country;
import th.ac.chula.fgxbio2.models.tables.Person;
import th.ac.chula.fgxbio2.models.tables.Province;
import th.ac.chula.fgxbio2.models.tables.Race;
import th.ac.chula.fgxbio2.models.tables.Region;
import th.ac.chula.fgxbio2.payload.request.PersonCustom;
import th.ac.chula.fgxbio2.payload.response.PersonForenseq;
import th.ac.chula.fgxbio2.payload.response.PersonsPages;
import th.ac.chula.fgxbio2.repository.tables.CountryRepository;
import th.ac.chula.fgxbio2.repository.tables.ForenseqRepository;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;
import th.ac.chula.fgxbio2.repository.tables.PersonRepository;
import th.ac.chula.fgxbio2.repository.tables.ProvinceRepository;
import th.ac.chula.fgxbio2.repository.tables.RaceRepository;
import th.ac.chula.fgxbio2.repository.tables.RegionRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private RaceRepository raceRepository;

	@Autowired
	private ForenseqRepository forenseqRepositiry;

	@Autowired
	private ForenseqSequenceRepository forenseqSequenceRepository;

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

	public Person getPersonById(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found :: " + id));
		return person;
	}

	public PersonsPages getAllPersonByPageAndSize(Integer page, Integer size) {
		List<Person> personList = personRepository.findAll(PageRequest.of(page, size)).toList();
		PersonsPages personResponse = new PersonsPages(personList, personRepository.count());
		return personResponse;
	}

	@Transactional
	public PersonForenseq getPersonForenseqById(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found :: " + id));
		List<ForenseqRow> fsA = new ArrayList<>();
		List<ForenseqRow> fsY = new ArrayList<>();
		List<ForenseqRow> fsX = new ArrayList<>();
		List<ForenseqRow> fsI = new ArrayList<>();
		List<ForenseqSequenceRow> fssA = new ArrayList<>();
		List<ForenseqSequenceRow> fssY = new ArrayList<>();
		List<ForenseqSequenceRow> fssX = new ArrayList<>();
		List<ForenseqSequenceRow> fssI = new ArrayList<>();
		List<Object[]> forenseqRowList = forenseqRepositiry.findAllForenseqByPersonId(id);
		List<Object[]> forenseqSequenceList = forenseqSequenceRepository.findAllForenseqSequenceByPersonId(id);

		for (int i = 0; i < forenseqRowList.size(); i++) {
			if (forenseqRowList.get(i)[0].toString().equals("Autosome")) {
				fsA.add(new ForenseqRow(forenseqRowList.get(i)[2].toString(), forenseqRowList.get(i)[1].toString(),
						forenseqRowList.get(i)[3].toString()));
			} else if (forenseqRowList.get(i)[0].toString().equals("Y")) {
				fsY.add(new ForenseqRow(forenseqRowList.get(i)[2].toString(), forenseqRowList.get(i)[1].toString(),
						forenseqRowList.get(i)[3].toString()));
			} else if (forenseqRowList.get(i)[0].toString().equals("X")) {
				fsX.add(new ForenseqRow(forenseqRowList.get(i)[2].toString(), forenseqRowList.get(i)[1].toString(),
						forenseqRowList.get(i)[3].toString()));
			} else {
				fsI.add(new ForenseqRow(forenseqRowList.get(i)[2].toString(), forenseqRowList.get(i)[1].toString(),
						forenseqRowList.get(i)[3].toString()));
			}
		}

		for (int i = 0; i < forenseqSequenceList.size(); i++) {
			if (forenseqSequenceList.get(i)[0].toString().equals("Autosome")) {
				fssA.add(new ForenseqSequenceRow(forenseqSequenceList.get(i)[1].toString(),
						forenseqSequenceList.get(i)[2].toString(), forenseqSequenceList.get(i)[3].toString(),
						Integer.parseInt(forenseqSequenceList.get(i)[4].toString())));
			} else if (forenseqSequenceList.get(i)[0].toString().equals("Y")) {
				fssY.add(new ForenseqSequenceRow(forenseqSequenceList.get(i)[1].toString(),
						forenseqSequenceList.get(i)[2].toString(), forenseqSequenceList.get(i)[3].toString(),
						Integer.parseInt(forenseqSequenceList.get(i)[4].toString())));
			} else if (forenseqSequenceList.get(i)[0].toString().equals("X")) {
				fssX.add(new ForenseqSequenceRow(forenseqSequenceList.get(i)[1].toString(),
						forenseqSequenceList.get(i)[2].toString(), forenseqSequenceList.get(i)[3].toString(),
						Integer.parseInt(forenseqSequenceList.get(i)[4].toString())));
			} else {
				fssI.add(new ForenseqSequenceRow(forenseqSequenceList.get(i)[1].toString(),
						forenseqSequenceList.get(i)[2].toString(), forenseqSequenceList.get(i)[3].toString(),
						Integer.parseInt(forenseqSequenceList.get(i)[4].toString())));
			}
		}

		PersonForenseq personFenForenseq = new PersonForenseq(person, fsA, fsX, fsY, fsI, fssA, fssX, fssY, fssI);
		return personFenForenseq;
	}

}
