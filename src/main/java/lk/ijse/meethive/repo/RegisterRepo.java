package lk.ijse.meethive.repo;

import lk.ijse.meethive.dto.RegistrationDTO;
import lk.ijse.meethive.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Registration,String> {
    boolean existsByEmail(String email);
    Registration findByEmail(String userName);
    //RegistrationDTO searchUser(String username);
}
