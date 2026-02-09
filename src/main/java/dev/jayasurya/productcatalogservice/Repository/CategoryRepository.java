package dev.jayasurya.productcatalogservice.Repository;

import dev.jayasurya.productcatalogservice.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
