package dotcom.si.notes.repositories;

import dotcom.si.notes.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
