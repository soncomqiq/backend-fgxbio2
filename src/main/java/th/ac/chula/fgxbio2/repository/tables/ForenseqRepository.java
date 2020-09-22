package th.ac.chula.fgxbio2.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Forenseq;

@Repository
public interface ForenseqRepository extends JpaRepository<Forenseq, Integer> {

}
