package th.ac.chula.fgxbio2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.AlleleAmount;
import th.ac.chula.fgxbio2.payload.response.LocusInfoGraph;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;

@Service
public class ForenseqSequenceService {
	@Autowired
	private ForenseqSequenceRepository forenseqSequenceRepository;

	public LocusInfoGraph getGraphInfoByChroAndLocus(String chromosome, String locus) {
		List<AlleleAmount> stats = forenseqSequenceRepository.findStatsByChromosomeAndLocus(chromosome, locus).stream()
				.map(obj -> new AlleleAmount(String.valueOf(obj[0]), Integer.parseInt(String.valueOf(obj[1]))))
				.collect(Collectors.toList());
		int numberOfHomo = forenseqSequenceRepository.findNumberOfHomo(chromosome, locus);
		int numberOfHetero = forenseqSequenceRepository.findNumberOfHetero(chromosome, locus);
		int numberOfTotal = numberOfHomo + numberOfHetero;

		LocusInfoGraph lIGraph = new LocusInfoGraph(stats, numberOfHetero, numberOfHomo, numberOfTotal,
				(double) numberOfHetero / (double) numberOfTotal, (double) numberOfHomo / (double) numberOfTotal);

		return lIGraph;
	}

	public Object getMapInfoByLocus(String locus) {
		// TODO Auto-generated method stub
		return null;
	}
}
