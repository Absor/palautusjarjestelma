package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
