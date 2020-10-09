package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.AlleleAmount;
import th.ac.chula.fgxbio2.payload.response.LocusInfoGraph;
import th.ac.chula.fgxbio2.payload.response.PatternAlignment;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;

@Service
public class ForenseqSequenceService {
	private static final String REPEATED_DATA = "1";
	private static final String ONE_TIME_DATA = "2";

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
			int patternIdx = 0;
			int sequenceIdx = 0;
			int count = 0;
			int count_total = 0;
			String seqAlignment = "";
			String sequence = tmp.get(i)[2].toString();

			while ((patternIdx < motif.size()) && (sequenceIdx <= sequence.length())) {

				if (count_total == (int) Float.parseFloat(allele) || sequenceIdx == sequence.length()) {
					seqAlignment += "(" + motif.get(patternIdx).substring(1) + ")" + count + " ";
					patternIdx += 1;
					count_total += count;
					count = 0;
					seqAlignment += sequence.substring(sequenceIdx);
				} else {
					String motifMode = motif.get(patternIdx).substring(0, 1);

					switch (motifMode) {
					case REPEATED_DATA:
						if (sequence.substring(sequenceIdx, sequenceIdx + motif.get(patternIdx).substring(1).length())
								.equals(motif.get(patternIdx).substring(1))) {
							count += 1;
							count_total += 1;
							sequenceIdx += motif.get(patternIdx).substring(1).length();
						} else {
							seqAlignment += "(" + motif.get(patternIdx).substring(1) + ")" + count + " ";
							count = 0;
							patternIdx += 1;
						}
						break;
					case ONE_TIME_DATA:
						seqAlignment += sequence.substring(sequenceIdx,
								sequenceIdx + motif.get(patternIdx).substring(1).length()) + " ";

						sequenceIdx += motif.get(patternIdx).substring(1).length();
						patternIdx += 1;
						break;
					default:
						seqAlignment += "No Repeated Data";
					}
				}
			}

			pAList.add(new PatternAlignment(tmp.get(i)[0].toString(), Integer.parseInt(tmp.get(i)[1].toString()), sequence, Integer.parseInt(tmp.get(i)[3].toString()), seqAlignment));
		}

		return pAList;
	}
}
