package dev.jayasurya.productcatalogservice.DTO;

import dev.jayasurya.productcatalogservice.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    Long id;
    String title;
    String description;
    Double price;
    CategoryDTO category;
    String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        if(this.category != null){
            product.setCategory(this.category.toCategory());
        }
        product.setImage(this.image);
        return product;
    }
}
