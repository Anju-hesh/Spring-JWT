package lk.ijse.gdse72;

import lk.ijse.gdse72.entity.Product;
import lk.ijse.gdse72.repository.ProductRepository;
import lk.ijse.gdse72.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Product product;

    @BeforeEach
    public void setUp(){
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.0)
                .quantity(10)
                .build();
    }

    @Test
    void shouldSaveProduct(){
        // Arrange
        // Mock the behavior of productRepository.save() to return the product
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Action
        Product savedProduct = productServiceImpl.createProduct(product);

        // Assert
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(product, savedProduct);
        Assertions.assertEquals(1L, product.getId());
        verify(productRepository , times(1)).save(product);
    }

    @Test
    void shouldUpdateProduct() {
        // Arrange
        Product updateProduct = Product.builder()
                .id(1L)
                .name("Update Product")
                .price(15.0)
                .quantity(5)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Action
        Product result = productServiceImpl.updateProduct(updateProduct);

        // Assert

        Assertions.assertEquals( "Update Product" , result.getName());
        Assertions.assertEquals(15.0, result.getPrice());
    }
}
