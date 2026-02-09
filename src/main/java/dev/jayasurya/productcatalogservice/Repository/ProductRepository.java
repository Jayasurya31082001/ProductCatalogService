package dev.jayasurya.productcatalogservice.Repository;

import dev.jayasurya.productcatalogservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
