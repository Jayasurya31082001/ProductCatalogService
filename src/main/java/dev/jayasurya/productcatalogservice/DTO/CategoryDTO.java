package dev.jayasurya.productcatalogservice.DTO;

import dev.jayasurya.productcatalogservice.Model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    Long id;
    String name;
    String description;

    public Category toCategory(){
        Category category = new Category();
        category.setId(this.id);
        category.setName(this.name);
        category.setDescription(this.description);
        return category;
    }
}
