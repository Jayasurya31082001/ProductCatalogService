package dev.jayasurya.productcatalogservice.Service;

import dev.jayasurya.productcatalogservice.Model.Product;
import dev.jayasurya.productcatalogservice.Model.State;
import dev.jayasurya.productcatalogservice.Repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Primary
public class StorageProductService implements  IProductService{
    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
      return  productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
           Product product1 = optionalProduct.get();

            product.setState(State.ACTIVE);
            return  productRepository.save(product);
        } else {
            product.setState(State.ACTIVE);
            return productRepository.save(product);
        }
    }

    @Override
    public Product updateProductById(Long id, Product product) {
       if(!productRepository.existsById(id)){
              return null;
       }
       product.setUpdatedAt(LocalDateTime.now());
       return  productRepository.save(product);
    }

    @Override
   public boolean deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setState(State.INACTIVE);
                productRepository.save(product);
                return true;
        }
        return false;
    }
}
