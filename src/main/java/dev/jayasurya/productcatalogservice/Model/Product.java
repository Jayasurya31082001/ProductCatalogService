package dev.jayasurya.productcatalogservice.Model;

import dev.jayasurya.productcatalogservice.DTO.FakeStoreDTO;
import dev.jayasurya.productcatalogservice.DTO.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends  BaseClass{

    String title;
    String description;
    Double price;
    Category category;
    String image;

   public ProductDTO toProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.getId());
        productDTO.setTitle(this.title);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        if (this.category != null) {
            productDTO.setCategory(this.category.toCategoryDTO());
        }
        productDTO.setImage(this.image);
        return productDTO;
    }

    public FakeStoreDTO toFakeStoreDTO() {
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId(this.getId());
        fakeStoreDTO.setTitle(this.title);
        fakeStoreDTO.setDescription(this.description);
        fakeStoreDTO.setPrice(this.price);
        if (this.category != null) {
            fakeStoreDTO.setCategory(this.category.getName());
        }
        fakeStoreDTO.setImage(this.image);
        return fakeStoreDTO;
    }
}
