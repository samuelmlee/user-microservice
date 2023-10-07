package platform.codingnomads.co.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.usermicroservice.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    List<Role> findByRoleIn(List<String> roleNames);
}
