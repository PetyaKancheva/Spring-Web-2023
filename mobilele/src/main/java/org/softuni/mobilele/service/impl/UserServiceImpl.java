package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.enums.RoleEnum;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserService;
import org.softuni.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;



    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;

    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
        currentUser.setLogged(true)
                    .setFirstName(userRegistrationDTO.firstName())
                    .setLastName(userRegistrationDTO.lastName())
                    .setEmail(userRegistrationDTO.email());

    }


    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        var userEntity = userRepository.findByEmail(userLoginDTO.email()).orElse(null);
        boolean loginSuccess = false;

        if (userEntity != null) {
            String rawPassword = userLoginDTO.password();
            String encodedPassword = userEntity.getPassword();
            loginSuccess =encodedPassword!=null &&  passwordEncoder.matches(rawPassword, encodedPassword);

            if (loginSuccess) {

                currentUser.setFirstName(userEntity.getFirstName())
                        .setLastName(userEntity.getLastName())
                        .setEmail(userEntity.getEmail())
                        .setLogged(true);
            }else{
                currentUser.logout();
            }

        }

        return loginSuccess;
    }

    @Override
    public void logout() {
        currentUser.logout();
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        return new UserEntity()
                .setActive(true)
                .setFirstName(userRegistrationDTO.firstName())
                .setLastName(userRegistrationDTO.lastName())
                .setEmail(userRegistrationDTO.email())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()))
                .setCreated(LocalDateTime.now())
               ;
    }


}
