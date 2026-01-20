package dev.jayasurya.productcatalogservice.Controller;

import dev.jayasurya.productcatalogservice.DTO.ProductDTO;
import dev.jayasurya.productcatalogservice.Exception.ProductNotFoundException;
import dev.jayasurya.productcatalogservice.Model.Product;
import dev.jayasurya.productcatalogservice.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping("api/v1/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        if(id == null || id <= 0) {
            throw  new IllegalArgumentException("Invalid product ID "+id );
        }
        Product product = productService.getProductById(id);
        if(product==null){
           throw new ProductNotFoundException("Product not found with ID: "+id);
        }
        return new ResponseEntity<>( product.toProductDTO(),HttpStatus.OK);

    }

    @GetMapping("/api/v1/products")
    public  ResponseEntity<List<ProductDTO>> getAllProducts() throws ProductNotFoundException {

        List<Product> products = productService.getAllProducts();
       if(products==null) {
           throw  new ProductNotFoundException("No products found");
       }
       List<ProductDTO> productDTOS = products.stream().map(Product::toProductDTO).toList();
       return new ResponseEntity<>(productDTOS,HttpStatus.OK);

    }


    /*Json body for testing create product API:
    {
  "id": 23,
  "title": "iphone 17",
  "price": 150000,
  "description": "apple product",
  "category":{
    "id":null,
    "name": "electronics",
    "description": null
   },
  "image": "http://apple.com"
}
     */
    @PostMapping("/api/v1/products")
    public  ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
       if(productDTO == null) {
           throw new IllegalArgumentException("Product data cannot be null" );
       }

       Product product = productDTO.toProduct();
        if(product == null ||  product.getTitle() == null || product.getPrice() == null || product.getPrice()<1)  {
            throw new IllegalArgumentException("Invalid product data" );
        }
        Product createdProduct = productService.createProduct(product);

        if(createdProduct==null) {
            throw new RuntimeException("Failed to create product" );
        }
        return new ResponseEntity<>( createdProduct.toProductDTO(),HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/products/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws ProductNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID " + id);
        }

        if (productDTO == null) throw new IllegalArgumentException("Product data cannot be null");

        Product product = productDTO.toProduct();
        if (product == null || product.getTitle() == null || product.getPrice() == null || product.getPrice() < 1) {
            throw new IllegalArgumentException("Invalid product data");
        }
        Product updatedProduct = productService.updateProductById(id, product);
        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        return new ResponseEntity<>(updatedProduct.toProductDTO(), HttpStatus.OK);
    }


}
