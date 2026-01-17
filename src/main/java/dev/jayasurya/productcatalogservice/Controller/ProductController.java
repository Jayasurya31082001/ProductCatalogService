package dev.jayasurya.productcatalogservice.Controller;

import dev.jayasurya.productcatalogservice.DTO.ProductDTO;
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
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        if(id == null || id <= 0) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(id);
        if(product==null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( product.toProductDTO(),HttpStatus.OK);

    }

    @GetMapping("/api/v1/products")
    public  ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<Product> products = productService.getAllProducts();
       if(products==null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       List<ProductDTO> productDTOS = products.stream().map(Product::toProductDTO).toList();
       return new ResponseEntity<>(productDTOS,HttpStatus.OK);

    }

    @PostMapping("/products")
    public  ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if(product == null || product.getName() == null || product.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }
}
