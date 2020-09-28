package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;

@Repository
public interface ForenseqSequenceRepository extends JpaRepository<ForenseqSequence, Integer> {
	@Query(value = "SELECT allele, count(*) as freq FROM forenseq_sequence ffs inner join forenseq ff on ffs.forenseq_id = ff.id where ff.chromosome_type = \"Autosome\" and ff.locus = \"TPOX\" GROUP BY ffs.Allele ORDER BY cast(ffs.Allele as unsigned)", nativeQuery = true)
	public List<?> findStatsByChromosomeAndLocus(@Param("chromosome") String chromosome, @Param("locus") String locus);
}
