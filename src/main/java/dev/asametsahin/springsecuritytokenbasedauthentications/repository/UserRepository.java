package dev.asametsahin.springsecuritytokenbasedauthentications.repository;

import dev.asametsahin.springsecuritytokenbasedauthentications.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByApiToken(String apiToken);
}
