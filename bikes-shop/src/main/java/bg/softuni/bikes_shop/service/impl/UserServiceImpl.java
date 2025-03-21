package bg.softuni.bikes_shop.service.impl;

import bg.softuni.bikes_shop.model.dto.UserRegisterDTO;
import bg.softuni.bikes_shop.model.dto.UserUpdateDTO;
import bg.softuni.bikes_shop.model.entity.UserEntity;
import bg.softuni.bikes_shop.model.events.UserRegistrationEvent;
import bg.softuni.bikes_shop.model.events.UserUpdateProfileEvent;
import bg.softuni.bikes_shop.repository.UserRepository;
import bg.softuni.bikes_shop.service.UserRoleService;
import bg.softuni.bikes_shop.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher appEventPublisher;
    private final EmailServiceImpl emailService;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, PasswordEncoder passwordEncoder, ApplicationEventPublisher appEventPublisher, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.appEventPublisher = appEventPublisher;
        this.emailService = emailService;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        userRepository.save(map(userRegisterDTO));
        appEventPublisher.publishEvent(new UserRegistrationEvent(
                "UserService",userRegisterDTO.email() ,userRegisterDTO.firstName()) );
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO, String email) {
            // TODO finess
        UserEntity existingUser = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + "not found!"));

        if (userRepository.findUserByEmail(userUpdateDTO.email()).isEmpty() ) {
            existingUser.setEmail(userUpdateDTO.email());
        } else {
            throw new IllegalArgumentException("User already exist with email:" + userUpdateDTO.email() + "!");

        }

        existingUser.setFirstName(userUpdateDTO.firstName());
        existingUser.setLastName(userUpdateDTO.lastName());
        existingUser.setAddress(userUpdateDTO.address());

        if (!passwordEncoder.matches(userUpdateDTO.newPassword(), existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(userUpdateDTO.newPassword()));
        } else {
            throw new IllegalArgumentException("Old password not matching!");
        }
        // TODO distinguish between email update!! cause now it is going to old email
        appEventPublisher.publishEvent(
                new UserUpdateProfileEvent("UserService-Update", email,userUpdateDTO.firstName(),String.valueOf(Instant.now())));


        userRepository.save(existingUser);

    }

    @Override
    @EventListener(UserUpdateProfileEvent.class)
    public void notify(UserUpdateProfileEvent event) {
        emailService.sendProfileUpdateEmail( event.getUserEmail(),event.getUserFirstName(), event.getTimeOfUpdate());
        System.out.println("Notification for profile update is sent to:  " + event.getUserEmail() + " !");
    }


    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        return new UserEntity()
                .setLogged(false)
                .setAuthenticated(false)
                .setFirstName(userRegisterDTO.firstName())
                .setLastName(userRegisterDTO.lastName())
                .setEmail(userRegisterDTO.email())
                .setAddress(userRegisterDTO.address())
                .setCountry(userRegisterDTO.country())
                .setRoles(Arrays.asList(userRoleService.getUserRoleByName("USER")))
                .setPassword(passwordEncoder.encode(userRegisterDTO.password()));


    }


}



