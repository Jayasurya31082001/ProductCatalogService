package dev.jayasurya.productcatalogservice.Model;

import dev.jayasurya.productcatalogservice.DTO.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Category extends  BaseClass {
    String name;
    String description;
    List<Product> productList;

    public CategoryDTO toCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(this.getId());
        categoryDTO.setName(this.name);
        categoryDTO.setDescription(this.description);
        return categoryDTO;
    }
}
