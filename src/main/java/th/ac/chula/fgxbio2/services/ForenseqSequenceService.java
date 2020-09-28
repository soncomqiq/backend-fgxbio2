package th.ac.chula.fgxbio2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.payload.response.LocusInfoGraph;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;

@Service
public class ForenseqSequenceService {
	@Autowired
	private ForenseqSequenceRepository forenseqSequenceRepository;
	
	public LocusInfoGraph getGraphInfoByChroAndLocus(String chromosome, String locus) {
		List<?> stats = forenseqSequenceRepository.findStatsByChromosomeAndLocus(chromosome, locus);
		return null;
	}
}
