package platform.codingnomads.co.usermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.usermicroservice.model.Role;
import platform.codingnomads.co.usermicroservice.model.User;
import platform.codingnomads.co.usermicroservice.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleService roleService;

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> optional;
        if ((optional = userRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createNewUser(User newUser) {
        List<String> roleNames = newUser.getAuthorities().stream()
                .map(Role::getRole).collect(Collectors.toList());

        List<Role> existingRoles = roleService.getRolesByNames(roleNames);

        if (roleNames.size() != existingRoles.size()) {
            throw new EntityNotFoundException("Some user authorities do not exist for user : " + newUser);
        }

        newUser.setAuthorities(existingRoles);
        return userRepository.save(newUser);
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findByUsername(updatedUser.getUsername());
        BeanUtils.copyProperties(updatedUser, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
