package th.ac.chula.fgxbio2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Province;
import th.ac.chula.fgxbio2.repository.tables.ProvinceRepository;

@Service
public class ProvinceService {
	@Autowired
	private ProvinceRepository provinceRepository;

	public List<Province> getProvincesByRegionId(Integer regionId) {
		List<Province> provincesList = provinceRepository.findAllByRegionId(regionId);
		return provincesList;
	}
}
