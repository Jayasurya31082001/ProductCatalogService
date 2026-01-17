package dev.jayasurya.productcatalogservice.Service;

import dev.jayasurya.productcatalogservice.Model.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

}
