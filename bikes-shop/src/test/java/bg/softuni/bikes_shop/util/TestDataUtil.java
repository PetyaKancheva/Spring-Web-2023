package bg.softuni.bikes_shop.util;

import bg.softuni.bikes_shop.model.dto.ProductAddDTO;
import bg.softuni.bikes_shop.model.entity.CurrencyEntity;
import bg.softuni.bikes_shop.model.entity.ProductEntity;
import bg.softuni.bikes_shop.repository.CurrencyRepository;
import bg.softuni.bikes_shop.repository.ItemRepository;
import bg.softuni.bikes_shop.repository.OrderRepository;
import bg.softuni.bikes_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestDataUtil {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;


    public void createExchangeRate(String currency, BigDecimal rate) {
        currencyRepository.save(
                new CurrencyEntity().setName(currency).setRate(rate));

    }

    public ProductAddDTO createTestProductDTO() {
        return
                new ProductAddDTO("test product",
                        "test description",
                        1000d, "testCategory", "urlTest");

    }


    public ProductEntity createTestProduct() {
        ProductEntity newProduct = new ProductEntity()
                .setName("test product")
                .setDescription("test description")
                .setPrice(BigDecimal.valueOf(1000))
                .setCategory("testCategory")
                .setPictureURL("urlTest");


        return productRepository.save(newProduct);
    }

    //
    public void cleanUp() {
        currencyRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
        itemRepository.deleteAll();
    }
}
