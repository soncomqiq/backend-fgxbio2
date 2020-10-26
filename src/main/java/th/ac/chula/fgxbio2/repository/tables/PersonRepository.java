package th.ac.chula.fgxbio2.repository.tables;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import th.ac.chula.fgxbio2.models.tables.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer>{

}
