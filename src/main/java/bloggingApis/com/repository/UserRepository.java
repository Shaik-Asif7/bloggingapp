package bloggingApis.com.repository;

import bloggingApis.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
}
