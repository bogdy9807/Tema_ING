package com.example.ing.service;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.api.dto.ProductPriceDto;
import com.example.ing.entity.ProductEntity;
import com.example.ing.mapper.ProductMapper;
import com.example.ing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.ing.util.ProductExceptionUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductDto> getAllProducts() {
		log.info("getAllProducts()");
		return productRepository.findAll().stream().map(ProductMapper::mapToProductDto).toList();
	}

	public ProductDto getProduct(String externalId) {
		log.info("getProduct({})", externalId);

		ProductEntity productEntity = getProductEntityByExtId(externalId);
		ProductDto productDto = ProductMapper.mapToProductDto(productEntity);

		log.debug("getProduct() finished: {}", productDto);
		return productDto;
	}

	public String addProduct(ProductDto productDto) {
		log.info("addProduct({})", productDto);

		if (Strings.isBlank(productDto.getExternalId())) {
			productDto.setExternalId(UUID.randomUUID().toString());
		}
		ProductEntity productEntity = ProductMapper.mapToProductEntity(productDto);
		validateProductInformation(productEntity);

		productRepository.save(productEntity);
		String externalId = productEntity.getExternalId();

		log.debug("addProduct() finished: {}", externalId);
		return externalId;
	}

	public void updateProductPrice(ProductPriceDto productPriceDto) {
		log.info("updateProductPrice({})", productPriceDto);

		ProductEntity productEntity = getProductEntityByExtId(productPriceDto.getExternalId());
		productRepository.updateProductPriceById(productEntity.getId(), productPriceDto.getPrice());

		log.info("updateProductPrice() finished");
	}

	private ProductEntity getProductEntityByExtId(String externalId) {
		return productRepository.findByExternalId(externalId).orElseThrow(() -> getProductNotFoundException(externalId));
	}

	private void validateProductInformation(ProductEntity productEntity) {
		productRepository.findByName(productEntity.getName()).ifPresent(prod -> throwProductAlreadyPresentException(NAME, prod.getName()));
		productRepository.findByExternalId(productEntity.getExternalId())
				.ifPresent(prod -> throwProductAlreadyPresentException(EXTERNAL_ID, prod.getExternalId()));
	}
}
