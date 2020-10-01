package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;

public class SampleRepositoryImpl implements SampleRepositoryCustom {
	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchMatchedSample(List<LocusAllele> lAList) {
		int matchedCount = 0;
		String query = "SELECT fs.sample_id, fs.sample_year FROM forenseq ff inner join "
				+ "forenseq_sequence ffs on ff.id = ffs.forenseq_id inner join samples fs "
				+ "on ff.sample_id = fs.id where ";

		for (LocusAllele lA : lAList) {
			if (matchedCount != 0) {
				query += " OR ";
			}

			if (lA.getAllele().split(",").length == 1) {
				query += String.format("(ff.locus = \"%s\" AND ffs.allele = %s)", lA.getLocus(), lA.getAllele());
				matchedCount += 1;
			} else {
				query += String.format("(ff.locus = \"%s\" AND ff.genotype = \"%S\")", lA.getLocus(), lA.getAllele());
				matchedCount += 2;
			}

		}

		query += String.format(" GROUP BY fs.sample_id, fs.sample_year HAVING count(*) = %d;", matchedCount);
		
		return entityManager.createNativeQuery(query).getResultList();
	}

}
