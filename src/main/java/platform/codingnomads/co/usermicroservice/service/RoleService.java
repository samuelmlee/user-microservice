package platform.codingnomads.co.usermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.usermicroservice.model.Role;
import platform.codingnomads.co.usermicroservice.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleByName(String roleName) throws EntityNotFoundException {
        Role role = roleRepository.findByRole(roleName);
        if (role == null) {
            throw new EntityNotFoundException("Role not found: " + roleName);
        }
        return role;
    }

    public List<Role> getRolesByNames(List<String> roleNames) {
        return roleRepository.findByRoleIn(roleNames);
    }
}
