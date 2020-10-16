package th.ac.chula.fgxbio2.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import th.ac.chula.fgxbio2.models.tables.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
