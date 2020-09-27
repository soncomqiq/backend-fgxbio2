package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.repository.tables.LocusRepository;

@Service
public class LocusService {
	@Autowired
	private LocusRepository locusRepository;

	public List<?> getAllLocus(){
		List<Object[]> allList = locusRepository.findDistinctAllLocus();
		List<String> aList = new ArrayList<>();
		List<String> xList = new ArrayList<>();
		List<String> yList = new ArrayList<>();
		
		for(Object[] row: allList) {
			System.out.println(row);
		}
		
		return allList;
	}
}
