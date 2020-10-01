package th.ac.chula.fgxbio2.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.models.tables.Kit;
import th.ac.chula.fgxbio2.payload.response.KitLocusListResponse;
import th.ac.chula.fgxbio2.repository.tables.KitRepository;

@Service
public class KitService {
	@Autowired
	private KitRepository kitRepository;

	@Transactional
	public KitLocusListResponse getAllKits() {
		List<Kit> aKits = kitRepository.findByChromosomeType("a").stream()
				.map(e -> new Kit(e.getId(), e.getKit(), null, null)).collect(Collectors.toList());
		List<Kit> yKits = kitRepository.findByChromosomeType("y").stream()
				.map(e -> new Kit(e.getId(), e.getKit(), null, null)).collect(Collectors.toList());
		List<Kit> xKits = kitRepository.findByChromosomeType("x").stream()
				.map(e -> new Kit(e.getId(), e.getKit(), null, null)).collect(Collectors.toList());
		return new KitLocusListResponse(aKits, yKits, xKits);
	}
}
