package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer>, SampleRepositoryCustom {

	@Query(value = "SELECT DISTINCT allele FROM forenseq fs INNER JOIN forenseq_sequence fss ON fs.id = fss.forenseq_id WHERE fs.locus = :locus ORDER BY cast(fss.allele as unsigned);", nativeQuery = true)
	List<Float> findAlleleByLocus(@Param(value = "locus") String locus);

}
