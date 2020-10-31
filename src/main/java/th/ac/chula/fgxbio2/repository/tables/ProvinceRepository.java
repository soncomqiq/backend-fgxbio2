package th.ac.chula.fgxbio2.repository.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.ac.chula.fgxbio2.models.tables.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

	List<Province> findAllByRegionId(Integer regionId);

}
