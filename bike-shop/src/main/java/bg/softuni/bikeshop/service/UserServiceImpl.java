package bg.softuni.bikeshop.service;

import bg.softuni.bikeshop.model.RoleEnum;
import bg.softuni.bikeshop.model.dto.RegisterUserDTO;
import bg.softuni.bikeshop.model.entity.UserEntity;
import bg.softuni.bikeshop.model.entity.UserRoleEntity;
import bg.softuni.bikeshop.repository.UserRepository;
import bg.softuni.bikeshop.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        UserEntity newUser = map(registerUserDTO);
        // TODO check better way to hard code USER role
        newUser.getRoles().add(userRoleRepository.findById((long) 1).orElseThrow());
        userRepository.save(newUser);


    }

    private UserEntity map(RegisterUserDTO registerUserDTO) {
        return new UserEntity()
                .setEmail(registerUserDTO.getEmail())
                .setFirstName(registerUserDTO.getFirstName())
                .setLastName(registerUserDTO.getLastName())
                .setAddress(registerUserDTO.getAddress())
                .setPassword(passwordEncoder.encode(registerUserDTO.getPassword()))
                .setCreated(LocalDate.now());
    }

}
