package bg.softuni.bikes_shop.controller;


import bg.softuni.bikes_shop.model.dto.CommentDTO;
import bg.softuni.bikes_shop.model.dto.ProductDTO;
import bg.softuni.bikes_shop.service.CurrencyService;
import bg.softuni.bikes_shop.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller

public class HomeController {
    private final static String ERROR_KEYWORD_NOT_FOUND_MSG =
            "No results found for %s !";
    private final static String ATTRIBUTE_MSG_NAME = "message";
    private final static List<String> CURRENCY_LIST = List.of("EUR", "BGN", "PLN", "USD");

    private final ProductService productService;
    private final CurrencyService currencyService;


    public HomeController(ProductService productService, CurrencyService currencyService) {
        this.productService = productService;
        this.currencyService = currencyService;
    }

    @ModelAttribute("categories")
    public List<String> categoriesList() {
        return productService.getDistinctCategories();
    }

    @ModelAttribute("listCurrencies")
    public List<String> currencyList() {
        return CURRENCY_LIST;
    }


    @GetMapping("/")
    private String allProducts(@RequestParam(defaultValue = "3") Integer s,
                               @RequestParam(defaultValue = "0") Integer p,
                               @RequestParam(defaultValue = "name: asc") String o,
                               Model model,@CookieValue(value = "currency", required = false)String cookie) {

        //TODO if old cookie exists that is not DE

        if (!model.containsAttribute("products")) {
            model.addAttribute("products", productService.getProducts(s, p, o));
        }
        if (!model.containsAttribute("currDTO")) {
            model.addAttribute("currDTO", currencyService.getCurrencyDTO(cookie));
        }

        return "index";
    }

    @GetMapping("/about")
    private String about() {
        return "/static/about";
    }

    @GetMapping("/services")
    private String services() {
        return "/static/services";
    }

    @GetMapping("/contacts")
    private String contacts() {
        return "/static/contacts";
    }

    @GetMapping("/api/comments")
    private String comments(Model model) {
        if (!model.containsAttribute("commentDTO")) {
            model.addAttribute("commentDTO", CommentDTO.empty());
        }
        return "comments";
    }


    @PostMapping("/search-result")
    private String search(Model model, String productToSearch, RedirectAttributes rAtt) {
        Page<ProductDTO> resultList = productService.searchForProducts(productToSearch);

        if (resultList.isEmpty()) {
            rAtt.addFlashAttribute(ATTRIBUTE_MSG_NAME, String.format(ERROR_KEYWORD_NOT_FOUND_MSG, productToSearch));
            return "redirect:/";
        }
        model.addAttribute("products", resultList);
        return "index";


    }
@PostMapping("/currency")
private String changeCurrency(HttpServletRequest request, HttpServletResponse response,String selectedCurrency ){

    currencyService.updateLocale(request,response,selectedCurrency);
    currencyService.updateCookie(request,response,selectedCurrency);

    return "redirect:/";
}



}
