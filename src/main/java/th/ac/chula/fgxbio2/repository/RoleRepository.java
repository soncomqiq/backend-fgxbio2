package th.ac.chula.fgxbio2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import th.ac.chula.fgxbio2.models.ERole;
import th.ac.chula.fgxbio2.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
