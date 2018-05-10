package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findById(int id);
}
