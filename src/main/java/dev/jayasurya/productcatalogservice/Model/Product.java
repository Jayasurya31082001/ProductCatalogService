package dev.jayasurya.productcatalogservice.Model;

import dev.jayasurya.productcatalogservice.DTO.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends  BaseClass{

    String name;
    String description;
    Double price;
    Category category;
    String imageurl;

   public ProductDTO toProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.getId());
        productDTO.setName(this.name);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        if (this.category != null) {
            productDTO.setCategory(this.category.toCategoryDTO());
        }
        productDTO.setImage(this.imageurl);
        return productDTO;
    }
}
