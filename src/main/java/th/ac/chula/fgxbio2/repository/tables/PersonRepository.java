package th.ac.chula.fgxbio2.repository.tables;

import org.springframework.data.jpa.repository.JpaRepository;

import th.ac.chula.fgxbio2.models.tables.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
