package com.ing.cjonatansoto.ms.infrastructure.repositories;

import com.ing.cjonatansoto.ms.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select p from Product p where p.name like %:name% and p.deletedAt is null")
    List<Product> findByNameContaining(String name);

}
