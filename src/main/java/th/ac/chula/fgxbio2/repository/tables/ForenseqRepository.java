package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Forenseq;

@Repository
public interface ForenseqRepository extends JpaRepository<Forenseq, Integer> {
	@Query(value = "SELECT fs.genotype, fs.locus, fs.qc_indicator FROM forenseq fs INNER JOIN samples sm ON fs.sample_id = sm.id INNER JOIN persons ps ON sm.person_id = ps.id where ps.id = :personId", nativeQuery = true)
	public List<Object[]> findAllForenseqByPersonId(@Param("personId") int personId);
}
