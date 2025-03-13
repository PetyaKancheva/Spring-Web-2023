package bg.softuni.bikes_shop.controller;

import bg.softuni.bikes_shop.model.UserRoleEnum;
import bg.softuni.bikes_shop.model.dto.CurrencyExchangeDTO;
import bg.softuni.bikes_shop.model.dto.ProductDTO;
import bg.softuni.bikes_shop.model.entity.UserRoleEntity;
import bg.softuni.bikes_shop.service.ProductService;
import bg.softuni.bikes_shop.service.UserService;
import bg.softuni.bikes_shop.util.CurrentCurrency;
import bg.softuni.bikes_shop.util.TestUser;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final ProductService productService;
    private final TestUser testUser;
    private final CurrentCurrency currentCurrency;

    public HomeController(ProductService productService, TestUser testUser, CurrentCurrency currentCurrency) {
        this.productService = productService;
        this.testUser = testUser;
        this.currentCurrency = currentCurrency;
    }

    @GetMapping("/")
    private String allProducts(Model model, @PageableDefault(size = 3,sort = "id") Pageable pageable  ) {
//        if(!model.containsAttribute("currencyDTO")){
//            CurrencyExchangeDTO eurCEDTO= new CurrencyExchangeDTO();
//            eurCEDTO.setRate(1.0);
//            eurCEDTO.setCurrency("EUR");
//            model.addAttribute("currencyDTO",eurCEDTO);
//        }

        model.addAttribute("products",productService.getProductsPageable(pageable));
        model.addAttribute("categories", productService.getDistinctCategories());
        model.addAttribute("testUser",testUser);
        model.addAttribute("listCurrencies", List.of("EUR","BGN","PLN","USD")) ;
        model.addAttribute("currentCurrency",currentCurrency);
        return "index";
    }

    @GetMapping("/about")
    private String about() {
        return "about";
    }

    @GetMapping("/services")
    private String services() {
        return "services";
    }

    @GetMapping("/contacts")
    private String contacts() {
        return "contacts";
    }
    @GetMapping("/logout")
    private String logout(){
       testUser.logout();
       return "redirect:/";
    }

    @PostMapping("/")
    private String post(CurrencyExchangeDTO ceDTO,RedirectAttributes rAtt){
        rAtt.addFlashAttribute("currencyDTO",ceDTO);
        // TODO fix currency being passing to model
        return "redirect:/";
    }
}
