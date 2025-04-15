package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {
    /*boolean existsByEmail(String email);*/

   /* User findByEmail(String email);*/

   // Check if user exists by email
   boolean existsByEmail(String userName);

    // Delete user by email (returns number of rows deleted)
    int deleteByEmail(String userName);

    @Query("SELECT u.email FROM User u")
    List<String> getUserEmails();

    // Correct method signature to return Optional<User>

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);


    @Query("SELECT COUNT(e) FROM User e")
    int countAll();

    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    int countActiveUsers();

}