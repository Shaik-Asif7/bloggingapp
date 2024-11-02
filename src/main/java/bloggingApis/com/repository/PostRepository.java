package bloggingApis.com.repository;

import bloggingApis.com.entity.Category;
import bloggingApis.com.entity.Post;
import bloggingApis.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

      List<Post> findByCategory(Category cat);

      List<Post> findByUser(User user);


}
