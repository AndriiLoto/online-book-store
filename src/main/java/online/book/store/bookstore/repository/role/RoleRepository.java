package online.book.store.bookstore.repository.role;

import java.util.Optional;
import online.book.store.bookstore.model.Role;
import online.book.store.bookstore.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
