package platform.codingnomads.co.usermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.usermicroservice.model.Role;
import platform.codingnomads.co.usermicroservice.model.User;
import platform.codingnomads.co.usermicroservice.repository.RoleRepository;
import platform.codingnomads.co.usermicroservice.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InitDataService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void initAppData() {
        Role userRole = Role.builder().role("ROLE_USER").build();
        Role adminRole = Role.builder().role("ROLE_ADMIN").build();

        if (roleRepo.findAll().isEmpty()) {
            roleRepo.saveAll(Arrays.asList(adminRole, userRole));
        }

        Role adminRoleSaved = roleRepo.findByRole("ROLE_ADMIN");


        User admin = User.builder()
                .id(null)
                .username("admin")
                .email("admin@example.com")
                .fullName("Super Admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(Collections.singletonList(adminRoleSaved))
                .build();

        if (userRepo.findAll().isEmpty()) {
            userRepo.save(admin);
        }
    }
}
