package marcus.example.APITest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
/*
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

 */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<userEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            userEntity user = userOptional.get();
            return new CustomUserDetails(user.getUsername(), user.getPassword());
        } else {
            throw new UsernameNotFoundException("Användare ej funnen: " + username);
        }
    }
}
