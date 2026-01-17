package dev.jayasurya.productcatalogservice.DTO;

import dev.jayasurya.productcatalogservice.Model.Category;
import dev.jayasurya.productcatalogservice.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {


    private  Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String imageurl;


    public Product toProductEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageurl(this.imageurl);
        Category category1 = new Category();
        category1.setName(this.category);
        product.setCategory(category1);
        return product;
    }
}
