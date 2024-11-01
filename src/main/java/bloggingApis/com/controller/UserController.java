package bloggingApis.com.controller;

import bloggingApis.com.entity.User;
import bloggingApis.com.payload.ApiResponse;
import bloggingApis.com.payload.UserDto;
import bloggingApis.com.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class UserController {
        private final UserService userService;
        public UserController(UserService userService) {
            this.userService = userService;
        }
        @PostMapping("/addUser")
        public ResponseEntity<UserDto> addUser(@Valid @RequestBody User user) {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }
        @GetMapping("/getAllUsers")
        public ResponseEntity<List<UserDto>> getUsers() {
            return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
        }
        @GetMapping("/user/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        }
        @PutMapping("/updateUser/{id}")
        public UserDto updateUser(@Valid @RequestBody User user,@PathVariable("id") int id){
            return userService.updateUser(user,id);
       }
       @DeleteMapping("/deleteUser/{id}")
       public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id){
             userService.deleteUser(id);
             return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
       }
}
