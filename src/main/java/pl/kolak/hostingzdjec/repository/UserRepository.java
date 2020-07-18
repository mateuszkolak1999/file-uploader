package pl.kolak.hostingzdjec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.hostingzdjec.model.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {
    UserApp findByUsername(String username);
}
