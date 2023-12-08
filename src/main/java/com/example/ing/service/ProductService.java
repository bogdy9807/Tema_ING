package com.example.ing.service;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.entity.ProductEntity;
import com.example.ing.mapper.ProductMapper;
import com.example.ing.repository.ProductRepository;
import com.example.ing.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product with external id %s was not found";

	private final ProductRepository productRepository;

	public ProductDto getProduct(String externalId) {
		log.info("getProduct({})", externalId);
		ProductEntity productEntity = productRepository.findByExternalId(externalId).orElseThrow(() -> getProductNotFoundException(externalId));
		ProductDto productDto = ProductMapper.mapToProductDto(productEntity);
		log.debug("getProduct() finished: {}", productDto);
		return productDto;
	}

	public String addProduct(ProductDto productDto) {
		log.info("addProduct({})", productDto);
		ProductEntity productEntity = ProductMapper.mapToProductEntity(productDto);
		if (Strings.isBlank(productEntity.getExternalId())) {
			productEntity.setExternalId(UUID.randomUUID().toString());
		}
		productRepository.save(productEntity);
		String externalId = productEntity.getExternalId();
		log.debug("addProduct() finished: {}", externalId);
		return externalId;
	}

	private ProductNotFoundException getProductNotFoundException(String externalId) {
		String exceptionMessage = String.format(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, externalId);
		return new ProductNotFoundException(exceptionMessage);
	}
}
