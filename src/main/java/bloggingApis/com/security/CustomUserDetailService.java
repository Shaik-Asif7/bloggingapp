package bloggingApis.com.security;

import bloggingApis.com.entity.User;
import bloggingApis.com.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository ;
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //loading user from database
        // Note : user name is taken as user's email
        User user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return user;
    }
}
