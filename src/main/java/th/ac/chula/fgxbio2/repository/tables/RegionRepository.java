package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

	List<Region> findAllByCountryId(Integer countryId);

}
