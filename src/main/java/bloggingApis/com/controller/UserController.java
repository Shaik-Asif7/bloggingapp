package bloggingApis.com.controller;

import bloggingApis.com.entity.User;
import bloggingApis.com.payload.UserDto;
import bloggingApis.com.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/blog")
public class UserController {
        private final UserService userService;
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping("/addUser")
        public ResponseEntity<UserDto> addUser(@RequestBody User user) {
            UserDto userDto=userService.addUser(user);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }

        @GetMapping("/getAllUsers")
        public ResponseEntity<List<UserDto>> getUsers() {
            List<UserDto> userDtos=userService.getUsers();
            return new ResponseEntity<>(userDtos,HttpStatus.OK);
        }
        @GetMapping("/user/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
            UserDto userDto=userService.getUserById(id);
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }
        @PutMapping("/updateUser/{id}")
        public UserDto updateUser(@RequestBody User user,@PathVariable("id") int id){
            return userService.updateUser(user,id);
       }
       @DeleteMapping("/deleteUser/{id}")
       public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
             userService.deleteUser(id);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
}
