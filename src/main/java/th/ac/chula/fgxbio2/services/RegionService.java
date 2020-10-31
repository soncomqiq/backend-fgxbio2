package th.ac.chula.fgxbio2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Region;
import th.ac.chula.fgxbio2.repository.tables.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;

	public List<Region> getRegionsByCountryId(Integer countryId) {
		List<Region> regionsList = regionRepository.findAllByCountryId(countryId);
		return regionsList;
	}
}
