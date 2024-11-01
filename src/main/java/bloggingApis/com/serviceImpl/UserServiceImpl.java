package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.User;
import bloggingApis.com.exception.UserException;
import bloggingApis.com.payload.UserDto;
import bloggingApis.com.repository.UserRepository;
import bloggingApis.com.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<UserDto> getUsers() {
      List<User> users=userRepository.findAll();
      List<UserDto> userDtos=users.stream()
              .map(user->convertEntitytoDto(user))
              .collect(Collectors.toList());

      return userDtos;
    }
    public UserDto getUserById(int id){
       User user=userRepository.findById(id).orElseThrow(()->new UserException("User not found"));
       return convertEntitytoDto(user);
    }
    public UserDto addUser(User user){
        UserDto userDto=convertEntitytoDto(user);
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    public UserDto updateUser(User user,int id){
        User extistingUser=userRepository.findById(id).orElseThrow(()->new UserException("User not found"));
            extistingUser.setName(user.getName());
            extistingUser.setEmail(user.getEmail());
            extistingUser.setAbout(user.getAbout());
            extistingUser.setPassword(user.getPassword()); // Assuming password field is not mandatory in the user entity. In a real scenario, password should be hashed before saving.
            userRepository.save(extistingUser);
            return convertEntitytoDto(extistingUser);
    }

    public void deleteUser(int id) {
        User user=userRepository.findById(id).orElseThrow(()-> new UserException("User not found"));
        userRepository.delete(user);
    }

    public UserDto convertEntitytoDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
//    public User dtoToEntity(UserDto userDto){
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        return user;
//    }

}
