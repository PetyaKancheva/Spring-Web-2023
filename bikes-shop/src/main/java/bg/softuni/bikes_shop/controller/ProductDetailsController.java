package bg.softuni.bikes_shop.controller;

import bg.softuni.bikes_shop.exceptions.CustomObjectNotFoundException;
import bg.softuni.bikes_shop.model.dto.ItemDTO;
import bg.softuni.bikes_shop.model.dto.ProductDTO;
import bg.softuni.bikes_shop.service.OrderService;
import bg.softuni.bikes_shop.service.ProductService;
import bg.softuni.bikes_shop.util.CurrentOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductDetailsController {
    private final ProductService productService;
    private final static String SUCCESSFUL_PURCHASE_MSG =
            "Successfully purchased: %s. Please go to order list to see your current order.";
    private final static String ATTRIBUTE_MSG_NAME = "onSuccess";


    private final CurrentOrder currentOrder;

    public ProductDetailsController(ProductService productService, CurrentOrder currentOrder) {
        this.productService = productService;

        this.currentOrder = currentOrder;
    }

    @GetMapping("/product/{id}")
    public String details(@PathVariable("id") String id, Model model) {

        ProductDTO singleProductDTO = productService.
                getSingleProduct(Long.parseLong(id))
                .orElseThrow(() -> new CustomObjectNotFoundException("Product with id " + id + " not found!"));

        model.addAttribute("singleProduct", singleProductDTO);

        return "product-details";
    }

    @PostMapping("/product/{id}")
    public String buy(@PathVariable("id") String id, String productName, String productPrice, Integer quantity, RedirectAttributes rAtt) {

        ItemDTO newItemDTO = new ItemDTO();
        newItemDTO.setProductID(Long.valueOf(id));
        newItemDTO.setProductName(productName);
        newItemDTO.setPrice(Double.parseDouble(productPrice));

        //TODO check if quantity >0
        newItemDTO.setQuantity(quantity);
        currentOrder.add(newItemDTO);

        rAtt.addFlashAttribute(ATTRIBUTE_MSG_NAME, String.format(SUCCESSFUL_PURCHASE_MSG, productName));

        return "shopping-cart";
    }


}

