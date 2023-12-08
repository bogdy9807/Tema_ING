package com.example.ing.repository;

import com.example.ing.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByExternalId(String externalId);
}
