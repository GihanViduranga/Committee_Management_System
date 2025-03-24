package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByEmail(String email);

    User findByEmail(String email);

}