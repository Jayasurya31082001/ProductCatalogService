package dev.jayasurya.productcatalogservice.Model;

import dev.jayasurya.productcatalogservice.DTO.CategoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends  BaseClass {
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Product> productList;

    public CategoryDTO toCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(this.getId());
        categoryDTO.setName(this.getName());
        categoryDTO.setDescription(this.getDescription());
        return categoryDTO;
    }
}
