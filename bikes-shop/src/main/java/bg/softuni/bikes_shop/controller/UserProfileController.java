package bg.softuni.bikes_shop.controller;

import bg.softuni.bikes_shop.model.CustomUserDetails;
import bg.softuni.bikes_shop.model.dto.UserSelfUpdateDTO;
import bg.softuni.bikes_shop.model.dto.UserUpdateDTO;
import bg.softuni.bikes_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserProfileController {
    private final UserService userService;
    private final static String SUCCESSFULLY_UPDATED_OWN_PROFILE_MSG =
            "Your profile is successfully updated!";
    private final static String ATTRIBUTE_MSG_NAME="message";

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")

    private String profile( Principal principal,Model model) {

        if (!model.containsAttribute("updateDTO")) {
            model.addAttribute("updateDTO",
                    new UserUpdateDTO(userService.getUserMainUpdateDTO(principal.getName()),UserSelfUpdateDTO.empty()));
        }


        return "user-profile";
    }

    @PostMapping("/user")
    private String profile(Principal principal, @Valid UserUpdateDTO updateDTO,
                           BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("updateDTO", updateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.updateDTO", bindingResult);
            return "redirect:/user";
        }

        userService.updateByUser(updateDTO,principal.getName());

        rAtt.addFlashAttribute(ATTRIBUTE_MSG_NAME, SUCCESSFULLY_UPDATED_OWN_PROFILE_MSG);

        return "redirect:/login?logout";
    }


}
