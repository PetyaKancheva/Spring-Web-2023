package bg.softuni.bikes_shop.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @NotEmpty(message ="Cannot be empty.")
        @Email
        String email,
        @NotEmpty(message ="Cannot be null.")
        @Size(min = 2, message= "Must be at least 2 characters.")
        String firstName,
        @NotEmpty(message ="Cannot be null.")
        @Size(min = 2, message= "Must be at least 2 characters.")
        String lastName,
       // @UniqueUserEmail TODO
        @NotEmpty(message ="Cannot be null.")
        @Size(min = 2, message= "Must be at least 2 characters.")
        String password,
        @NotEmpty(message ="Cannot be empty.")
        String confirmPassword)  {
    public static UserRegisterDTO empty() {
        return new UserRegisterDTO(null, null, null, null, null);
    }


    }
