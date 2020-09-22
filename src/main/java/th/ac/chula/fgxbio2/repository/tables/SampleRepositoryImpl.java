package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;

public class SampleRepositoryImpl implements SampleRepositoryCustom{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<?> searchMatchedSample(List<LocusAllele> lAList) {
		String query = "SELECT b.Sample_Year, b.Sample_ID FROM (SELECT a.Sample_Year, a.Sample_ID, COUNT(*) AS amount FROM (SELECT * FROM (select * from forenseq UNION select * from forenseqY UNION select * from forenseqX) tmp WHERE ";
		int length = lAList.size();
		for (int i = 0; i < length; i++) {
			LocusAllele rlc = lAList.get(i);
			if (i == 0) {
				query += " tmp._type = \"Yes\" and ( tmp.locus = \"" + rlc.getLocus() + "\" and" + " tmp.allele = "
						+ rlc.getAllele() + " )";
			} else {
				query += " or ( tmp.locus = \"" + rlc.getLocus() + "\" and" + " tmp.allele = " + rlc.getAllele() + " )";
			}
		}
		query += ") a GROUP BY a.Sample_Year, a.Sample_ID) b WHERE b.amount = " + length + ";";
		
		System.out.println(query);
		
		return entityManager.createNativeQuery(query).getResultList();
	}

}
