package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.payload.response.KitLocusResponse;
import th.ac.chula.fgxbio2.repository.tables.LocusRepository;

@Service
public class LocusService {
	@Autowired
	private LocusRepository locusRepository;

	public KitLocusResponse getAllLocus(){
		List<Object[]> allList = locusRepository.findDistinctAllLocus();
		List<String> aList = new ArrayList<>();
		List<String> xList = new ArrayList<>();
		List<String> yList = new ArrayList<>();
		
		for(Object[] row: allList) {
			String locus = row[0].toString();
			String chromosome = row[1].toString();
			switch(chromosome) {
			case "Autosome":
				aList.add(locus);
				break;
			case "X":
				xList.add(locus);
				break;
			case "Y":
				yList.add(locus);
				break;
			default:
				break;
			}
		}
		
		return new KitLocusResponse(aList, xList, yList);
	}
}
