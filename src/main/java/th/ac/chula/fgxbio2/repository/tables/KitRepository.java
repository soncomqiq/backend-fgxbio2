package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import th.ac.chula.fgxbio2.models.tables.Kit;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface KitRepository extends JpaRepository<Kit, Integer>{
	List<Kit> findByChromosomeType(String chromosomeType);
}
