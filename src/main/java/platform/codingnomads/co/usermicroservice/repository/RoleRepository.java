package platform.codingnomads.co.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.usermicroservice.model.Role;
import platform.codingnomads.co.usermicroservice.model.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRole(Roles role);
}
