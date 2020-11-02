package th.ac.chula.fgxbio2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Race;
import th.ac.chula.fgxbio2.repository.tables.RaceRepository;

@Service
public class RaceService {
	@Autowired
	private RaceRepository raceRepository;

	public List<Race> getAllRaces() {
		List<Race> raceList = raceRepository.findAll();
		return raceList;
	}
}
