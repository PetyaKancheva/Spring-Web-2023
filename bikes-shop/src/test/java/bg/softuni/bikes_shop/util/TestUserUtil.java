package bg.softuni.bikes_shop.util;

import bg.softuni.bikes_shop.model.UserRoleEnum;
import bg.softuni.bikes_shop.model.entity.UserEntity;
import bg.softuni.bikes_shop.repository.UserRepository;
import bg.softuni.bikes_shop.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestUserUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));
    }

    public UserEntity createTestEmployee(String email) {
        return createUser(email, List.of(UserRoleEnum.EMPLOYEE));
    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {

        var roleEntities = userRoleRepository.findAllByNameIn(roles);
        UserEntity newUser = new UserEntity()
                .setEnabled(false)
                .setFirstName("First name test")
                .setLastName("Last name test")
                .setEmail(email)
                .setAddress("Address Test")
                .setCountry("Country Test")
                .setRoles(roleEntities)
                .setPassword("test1234");

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }

}
