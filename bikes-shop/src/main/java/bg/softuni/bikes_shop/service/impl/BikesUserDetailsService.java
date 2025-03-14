package bg.softuni.bikes_shop.service.impl;

import bg.softuni.bikes_shop.model.CustomUserDetails;
import bg.softuni.bikes_shop.model.entity.UserEntity;
import bg.softuni.bikes_shop.model.entity.UserRoleEntity;
import bg.softuni.bikes_shop.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BikesUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BikesUserDetailsService( UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findUserByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User with email: "+ email+" not found!"));

        return new CustomUserDetails(userEntity);
//        return mapDetails((userEntity));


    }

    @PostConstruct
    public void init() {
        userRepository.put("user", new CustomUserDetails.Builder().withFirstName("Mark")
                .withLastName("Johnson")
                .withEmail("mark.johnson@email.com")
                .withUsername("user")
                .withPassword(passwordEncoder.encode("password"))
                .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build());
        userRegistry.put("admin", new CustomUserDetails.Builder().withFirstName("James")
                .withLastName("Davis")
                .withEmail("james.davis@email.com")
                .withUsername("admin")
                .withPassword(passwordEncoder.encode("password"))
                .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build());
    }

//    private static UserDetails mapDetails(UserEntity userEntity){
//        return User
//                .withUsername(userEntity.getEmail())
//                .password(userEntity.getPassword())
//                .authorities(userEntity.getRoles().stream().map(BikesUserDetailsService::mapToAuthority).toList())
//                .build();
//
//    }

//    private static GrantedAuthority mapToAuthority(UserRoleEntity userRoleEntity){
//        return new SimpleGrantedAuthority("ROLE_" +userRoleEntity.getName().name());
//
//    }

}
