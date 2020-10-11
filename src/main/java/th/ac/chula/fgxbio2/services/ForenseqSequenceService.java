package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.AlleleAmount;
import th.ac.chula.fgxbio2.payload.response.LocusInfoGraph;
import th.ac.chula.fgxbio2.payload.response.PatternAlignment;
import th.ac.chula.fgxbio2.payload.response.iSNPResponse;
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

	public List<PatternAlignment> getPatternAlignmentByLocusAndAllele(String locus, String allele) {
		List<PatternAlignment> pAList = new ArrayList<>();
		List<String> motif = forenseqSequenceRepository.findMotifLocus(locus);
		List<Object[]> tmp = forenseqSequenceRepository.findAllForenseqTable(locus, Float.parseFloat(allele));

		for (int i = 0; i < tmp.size(); i++) {
			int currentSequenceIdx = 0;
			String seqAlignment = "";
			String sequence = tmp.get(i)[2].toString();
			int sequenceLength = sequence.length();

			for (int j = 0; j < motif.size(); j++) {
				String currentMotif = motif.get(j);
				int currentMotifSize = currentMotif.length();

				if (sequence.indexOf(currentMotif, currentSequenceIdx) == -1) {
					continue;
				}

				int fTargetIndex = sequence.indexOf(currentMotif, currentSequenceIdx);
				String beforePattern = sequence.substring(currentSequenceIdx, fTargetIndex);
				seqAlignment += beforePattern;
				currentSequenceIdx += beforePattern.length();

				int numberOfCurrentMotif = 0;
				while (currentSequenceIdx + currentMotifSize <= sequenceLength && sequence
						.substring(currentSequenceIdx, currentSequenceIdx + currentMotifSize).equals(currentMotif)) {
					numberOfCurrentMotif++;
					currentSequenceIdx += currentMotifSize;
				}

				seqAlignment += String.format(" (%s)%d ", currentMotif, numberOfCurrentMotif);
			}

			if (currentSequenceIdx < sequenceLength) {
				seqAlignment += sequence.substring(currentSequenceIdx, sequenceLength);
			}

			pAList.add(new PatternAlignment(tmp.get(i)[0].toString(), Integer.parseInt(tmp.get(i)[1].toString()),
					sequence, Integer.parseInt(tmp.get(i)[3].toString()), seqAlignment));
		}

		return pAList;
	}

	public List<iSNPResponse> getStatsISNP() {
		List<iSNPResponse> result = forenseqSequenceRepository
				.findStatByISNPGroupByLocusAndAllele().stream().map(e -> new iSNPResponse(e[0].toString(),
						e[1].toString(), Integer.parseInt(e[2].toString())))
				.collect(Collectors.toList());
		return result;
	}
}
