package webdev.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import webdev.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findById(int id);
}
