package com.ing.cjonatansoto.ms.repositories;

import com.ing.cjonatansoto.ms.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
