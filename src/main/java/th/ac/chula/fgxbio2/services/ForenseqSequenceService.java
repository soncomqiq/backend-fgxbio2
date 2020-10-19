package th.ac.chula.fgxbio2.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.AlleleAmount;
import th.ac.chula.fgxbio2.payload.response.LocusInfoGraph;
import th.ac.chula.fgxbio2.payload.response.PatternAlignment;
import th.ac.chula.fgxbio2.payload.response.StatsMap;
import th.ac.chula.fgxbio2.payload.response.iSNPResponse;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;

@Service
public class ForenseqSequenceService {

	@Autowired
	private ForenseqSequenceRepository forenseqSequenceRepository;

	private final List<String> COLOR_LIST = new ArrayList<>(Arrays.asList("rgba(255,87,34,0.8)", "rgba(255,255,0,0.8)",
			"rgba(255,0,255,0.8)", "rgba(0,0,255,0.8)", "rgba(0,255,0,0.8)", "rgba(128,0,128,0.8)", "rgba(255,0,0,0.8)",
			"rgba(0,255,255,0.8)", "rgba(128,128,0,0.8)", "rgba(0,0,128,0.8)", "rgba(88,214,141,0.8)",
			"rgba(52,152,219,0.8)", "rgba(236,112,99,0.8)", "rgba(220,118,51,0.8)"));

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

	public List<StatsMap> getMapInfoByLocus(String locus) {
		List<Object[]> statMap = forenseqSequenceRepository.findStatsMapByLocus(locus);
		Map<String, String> mapColor = new HashMap<String, String>();
		List<StatsMap> result = new ArrayList<StatsMap>();

		int count_color = 0;
		for (int i = 0; i < statMap.size(); i++) {
			if (mapColor.get(statMap.get(i)[3].toString()) == null) {
				mapColor.put(statMap.get(i)[3].toString(), COLOR_LIST.get(count_color++));
			}

			result.add(new StatsMap(statMap.get(i)[0].toString(), Float.parseFloat(statMap.get(i)[1].toString()),
					Float.parseFloat(statMap.get(i)[2].toString()), Float.parseFloat(statMap.get(i)[3].toString()),
					Integer.parseInt(statMap.get(i)[4].toString()), mapColor.get(statMap.get(i)[3].toString())));
		}

		return result;
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
		List<iSNPResponse> result = forenseqSequenceRepository.findStatByISNPGroupByLocusAndAllele().stream()
				.map(e -> new iSNPResponse(e[0].toString(), e[1].toString(), Integer.parseInt(e[2].toString())))
				.collect(Collectors.toList());
		return result;
	}
}
