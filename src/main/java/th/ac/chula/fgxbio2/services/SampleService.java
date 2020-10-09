package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.SampleIDYear;
import th.ac.chula.fgxbio2.payload.request.LocusAllele;
import th.ac.chula.fgxbio2.payload.response.MatchedSampleResponse;
import th.ac.chula.fgxbio2.repository.tables.SampleRepository;

@Service
public class SampleService {
	@Autowired
	private SampleRepository sampleRepository;

	@Transactional
	public MatchedSampleResponse getPersonsByLocusAllele(List<LocusAllele> lAList, Boolean isAuth) {
		List<SampleIDYear> matchedSP = sampleRepository.searchMatchedSample(lAList).stream()
				.map(obj -> new SampleIDYear(String.valueOf(obj[0]), Integer.parseInt(String.valueOf(obj[1]))))
				.collect(Collectors.toList());
		if(isAuth) {			
			return new MatchedSampleResponse(matchedSP, (int) sampleRepository.count(), matchedSP.size());
		}else {
			return new MatchedSampleResponse(new ArrayList<>(), (int) sampleRepository.count(), matchedSP.size());
		}
	}

	public List<Float> getAlleleByLocus(String locus) {
		List<Float> alleleList = sampleRepository.findAlleleByLocus(locus);
		return alleleList;
	}

}
