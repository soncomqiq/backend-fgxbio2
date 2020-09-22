package th.ac.chula.fgxbio2.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;
import th.ac.chula.fgxbio2.repository.tables.SampleRepository;

@Service
public class SampleService {
	@Autowired
	private SampleRepository sampleRepository;

	@Transactional
	public List<?> getPersonsByLocusAllele(List<LocusAllele> lAList){
		return sampleRepository.searchMatchedSample(lAList);
	}
}
