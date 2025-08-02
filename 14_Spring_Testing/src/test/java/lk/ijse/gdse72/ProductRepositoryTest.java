package lk.ijse.gdse72;

import lk.ijse.gdse72.entity.Product;
import lk.ijse.gdse72.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct(){
        Product product = Product.builder()
                .name("Product 1")
                .price(100.0)
                .quantity(10)
                .build();

        Product savedProduct = productRepository.save(product);
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertEquals("Product 1", savedProduct.getName());
        Assertions.assertEquals(100.0, savedProduct.getPrice());
        Assertions.assertEquals(10, savedProduct.getQuantity());
    }

    @Test
    void shouldFindAllProducts() {
        Product product1 = Product.builder()
                .name("Product 1")
                .price(100.0)
                .quantity(10)
                .build();

        Product product2 = Product.builder()
                .name("Product 2")
                .price(200.0)
                .quantity(20)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }
}
