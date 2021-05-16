package dev.asametsahin.springsecuritytokenbasedauthentications.seeder;

import dev.asametsahin.springsecuritytokenbasedauthentications.entity.User;
import dev.asametsahin.springsecuritytokenbasedauthentications.repository.UserRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserSeeder(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @EventListener
    public void createUsers(ContextRefreshedEvent event) {
        Iterable<User> userList = userRepository.findAll();

        if (IterableUtils.size(userList) == 0) {
            for (int i = 1; i <= 10; i++) {
                String username = "user" + i;
                String password = passwordEncoder.encode("password");
                String email = username + "@example.com";
                User user = new User(username, password, email);
                userRepository.save(user);
            }
        }
    }
}
