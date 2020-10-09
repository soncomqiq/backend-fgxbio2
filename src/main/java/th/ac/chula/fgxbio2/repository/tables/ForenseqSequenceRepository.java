package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;

@Repository
public interface ForenseqSequenceRepository extends JpaRepository<ForenseqSequence, Integer> {
	@Query(value = "SELECT allele, count(*) as freq FROM forenseq_sequence ffs inner join forenseq ff on ffs.forenseq_id = ff.id where ff.chromosome_type = :chromosome and ff.locus = :locus GROUP BY ffs.Allele ORDER BY cast(ffs.Allele as unsigned)", nativeQuery = true)
	public List<Object[]> findStatsByChromosomeAndLocus(@Param("chromosome") String chromosome,
			@Param("locus") String locus);

	@Query(value = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM forenseq_sequence ffs inner join forenseq ff on ffs.forenseq_id = ff.id where ff.chromosome_type = :chromosome and ff.locus = :locus GROUP BY ff.genotype, ff.sample_id HAVING count(*) = 2) sum", nativeQuery = true)
	public int findNumberOfHetero(@Param("chromosome") String chromosome, @Param("locus") String locus);

	@Query(value = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM forenseq_sequence ffs inner join forenseq ff on ffs.forenseq_id = ff.id where ff.chromosome_type = :chromosome and ff.locus = :locus GROUP BY ff.genotype, ff.sample_id HAVING count(*) = 1) sum", nativeQuery = true)
	public int findNumberOfHomo(@Param("chromosome") String chromosome, @Param("locus") String locus);

	@Query(value = "SELECT pattern FROM pattern_alignment pa WHERE pa.locus = :locus ORDER BY pa.seq_no", nativeQuery = true)
	public List<String> findMotifLocus(@Param("locus") String locus);

	@Query(value = "SELECT sp.sample_id, sp.sample_year, ffs.sequence, ffs.read_count FROM forenseq fs INNER JOIN forenseq_sequence ffs ON fs.id = ffs.forenseq_id INNER JOIN samples sp ON fs.sample_id = sp.id WHERE fs.locus = :locus AND ffs.allele = :allele", nativeQuery = true)
	public List<Object[]> findAllForenseqTable(@Param("locus") String locus, @Param("allele") float allele);
}
