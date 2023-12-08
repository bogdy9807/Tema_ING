package com.example.ing.repository;

import com.example.ing.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByExternalId(String externalId);

	@Transactional
	@Modifying
	@Query("update ProductEntity set price = :price where id = :id")
	void updateProductPriceById(@Param("id") Long id, @Param("price") Float price);
}
