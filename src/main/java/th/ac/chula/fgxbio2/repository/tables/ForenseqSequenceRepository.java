package th.ac.chula.fgxbio2.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;

@Repository
public interface ForenseqSequenceRepository extends JpaRepository<ForenseqSequence, Integer>{

}
