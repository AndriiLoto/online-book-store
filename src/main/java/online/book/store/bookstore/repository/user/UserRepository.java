package online.book.store.bookstore.repository.user;

import online.book.store.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String attr0);
}
