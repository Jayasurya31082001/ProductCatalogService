package dev.jayasurya.productcatalogservice.Service;

import dev.jayasurya.productcatalogservice.Exception.ProductAlreadyExistException;
import dev.jayasurya.productcatalogservice.Exception.ProductNotFoundException;
import dev.jayasurya.productcatalogservice.Model.Product;

import java.util.List;

public interface IProductService {

   public Product getProductById(Long id) ;

    public List<Product> getAllProducts() ;

    public Product createProduct(Product product) ;

    public Product updateProductById(Long id, Product product) ;

    public boolean deleteProductById(Long id) ;
}
