package bloggingApis.com.service;

import bloggingApis.com.entity.User;
import bloggingApis.com.payload.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
       public  List<UserDto> getUsers();
       public UserDto getUserById(int id);
       public UserDto addUser(User user);
       public UserDto updateUser(User user,int id);
       public void deleteUser(int id);
}
