package dev.jayasurya.productcatalogservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    Long id;
    String name;
    String description;
    Double price;
    CategoryDTO category;
    String image;
}
