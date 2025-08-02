package lk.ijse.gdse72.service;

import lk.ijse.gdse72.entity.Product;
import lk.ijse.gdse72.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct( Product product) {
        Product excproduct = getProductById(product.getId());
        excproduct.setName(product.getName());
        excproduct.setPrice(product.getPrice());
        excproduct.setQuantity(product.getQuantity());
        return productRepository.save(excproduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
