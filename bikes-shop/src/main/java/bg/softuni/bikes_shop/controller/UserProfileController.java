package bg.softuni.bikes_shop.controller;

import bg.softuni.bikes_shop.model.CustomUserDetails;
import bg.softuni.bikes_shop.model.dto.UserUpdateDTO;
import bg.softuni.bikes_shop.model.events.UserUpdateProfileEvent;
import bg.softuni.bikes_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;

@Controller
public class UserProfileController {
    private final UserService userService;
    private final static String SUCCESSFUL_UPDATE_MSG =
            "Your profile is successfully updated!";
    private final static String ATTRIBUTE_MSG_NAME="onSuccess";


    public UserProfileController(UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
    }

    @GetMapping("/user")

    private String profile(@AuthenticationPrincipal CustomUserDetails currentUser, Model model) {
        if (!model.containsAttribute("userUpdateDTO")) {
            model.addAttribute("userUpdateDTO", UserUpdateDTO.empty());
        }
        model.addAttribute("currentUser", currentUser);

        return "user-profile";
    }

    @PostMapping("/user")
    private String profile(@AuthenticationPrincipal CustomUserDetails currentUser, @Valid UserUpdateDTO userUpdateDTO, BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userUpdateDTO", userUpdateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userUpdateDTO", bindingResult);
            return "redirect:/user";
        }
        UserDetails newUser = User.withUsername("p@gmail.com")
                .password("test")
                        .disabled(false).build();
//        UserDetails ud =
//                User.
//                        withUsername(..).
//        password(..).
//        authorities(..).
//        build();
        userService.update(userUpdateDTO, currentUser.getUsername());

        rAtt.addFlashAttribute(ATTRIBUTE_MSG_NAME,SUCCESSFUL_UPDATE_MSG );

        return "redirect:/login";
//        return "redirect:/logout";
    }


}
