package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;

public interface SampleRepositoryCustom {
	public List<Object[]> searchMatchedSample(List<LocusAllele> lAList);
}
