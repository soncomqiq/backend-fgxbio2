package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Locus;

@Repository
public interface LocusRepository extends JpaRepository<Locus, Integer> {

	@Query(value = "SELECT DISTINCT locus, chromosome_type FROM forenseq"
			+ " fq WHERE fq.chromosome_type = 'Autosome' OR fq.chromosome_type = 'x' "
			+ "OR fq.chromosome_type = 'y';", nativeQuery = true)
	public List<Object[]> findDistinctAllLocus();
}
