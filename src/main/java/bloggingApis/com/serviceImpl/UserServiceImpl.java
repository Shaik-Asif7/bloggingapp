package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.User;
import bloggingApis.com.exception.UserCustomException;
import bloggingApis.com.payload.UserDto;
import bloggingApis.com.repository.UserRepository;
import bloggingApis.com.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper){
        this.userRepository=userRepository;
        this.mapper=mapper;
    }
    public List<UserDto> getUsers() {
      List<User> users=userRepository.findAll();
      List<UserDto> userDtos=users.stream()
              .map(user->convertEntitytoDto(user))
              .collect(Collectors.toList());
      return userDtos;
    }
    public UserDto getUserById(int id){
       User user=userRepository.findById(id).orElseThrow(()->new UserCustomException("User not found with id %d",id));
       return convertEntitytoDto(user);
    }
    public UserDto addUser(User user){
        UserDto userDto=convertEntitytoDto(user);
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    public UserDto updateUser(User user,int id){
        User extistingUser=userRepository.findById(id).orElseThrow(()->new UserCustomException("User not found with id %d",id));
            extistingUser.setName(user.getName());
            extistingUser.setEmail(user.getEmail());
            extistingUser.setAbout(user.getAbout());
            extistingUser.setPassword(user.getPassword()); // Assuming password field is not mandatory in the user entity. In a real scenario, password should be hashed before saving.
            userRepository.save(extistingUser);
            return convertEntitytoDto(extistingUser);
    }

    public void deleteUser(int id) {
        User user=userRepository.findById(id).orElseThrow(()-> new UserCustomException("User not found with id %d",id));
        userRepository.delete(user);
    }
     public UserDto convertEntitytoDto(User user){
        return mapper.map(user, UserDto.class);
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
