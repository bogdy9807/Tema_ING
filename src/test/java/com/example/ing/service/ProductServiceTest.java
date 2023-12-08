package com.example.ing.service;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.entity.ProductEntity;
import com.example.ing.mapper.ProductMapper;
import com.example.ing.repository.ProductRepository;
import com.example.ing.service.exception.ProductAlreadyPresentException;
import com.example.ing.service.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	private static final String EXTERNAL_ID = "externalId";
	private static final String NAME = "Name";
	private static final Float PRICE = 10F;

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	void whenGetProduct_expectProductReturned() {
		ProductEntity productEntity = getProductEntity();
		when(productRepository.findByExternalId(EXTERNAL_ID)).thenReturn(Optional.of(productEntity));

		ProductDto foundProduct = productService.getProduct(EXTERNAL_ID);

		assertProductFields(foundProduct);
	}

	@Test
	void whenGetProductAndProductIsNotFound_expectProductNotFoundException() {
		assertThrows(ProductNotFoundException.class, () -> productService.getProduct(EXTERNAL_ID));
	}

	@Test
	void whenGetProducts_expectProductsReturned() {
		when(productRepository.findAll()).thenReturn(List.of(getProductEntity()));

		List<ProductDto> productDtoList = productService.getAllProducts();

		assertEquals(1, productDtoList.size());
		assertProductFields(productDtoList.get(0));
	}

	@Test
	void whenAddProduct_expectProductSaved() {
		ProductEntity productEntity = getProductEntity();
		ProductDto productDto = ProductMapper.mapToProductDto(productEntity);

		String extId = productService.addProduct(productDto);

		assertEquals(EXTERNAL_ID, extId);
		verify(productRepository).save(productEntity);
	}

	@Test
	void whenAddProductAndNameIsAlreadyUsed_expectProcessAlreadyPresentException() {
		ProductEntity productEntity = getProductEntity();
		ProductDto productDto = ProductMapper.mapToProductDto(productEntity);
		when(productRepository.findByName(NAME)).thenReturn(Optional.of(productEntity));

		assertThrows(ProductAlreadyPresentException.class, () -> productService.addProduct(productDto));
	}

	@Test
	void whenAddProductAndExternalIdIsAlreadyUsed_expectProcessAlreadyPresentException() {
		ProductEntity productEntity = getProductEntity();
		ProductDto productDto = ProductMapper.mapToProductDto(productEntity);
		when(productRepository.findByExternalId(EXTERNAL_ID)).thenReturn(Optional.of(productEntity));

		assertThrows(ProductAlreadyPresentException.class, () -> productService.addProduct(productDto));
	}

	@Test
	void whenAddProductAndExternalIdIsEmpty_expectExternalIdGenerated() {
		ProductDto productDto = ProductDto.builder()
				.name(NAME)
				.price(PRICE)
				.build();

		String extId = productService.addProduct(productDto);

		assertNotNull(extId);
	}

	private void assertProductFields(ProductDto foundProduct) {
		assertEquals(NAME, foundProduct.getName());
		assertEquals(EXTERNAL_ID, foundProduct.getExternalId());
		assertEquals(PRICE, foundProduct.getPrice());
	}

	private static ProductEntity getProductEntity() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setExternalId(EXTERNAL_ID);
		productEntity.setName(NAME);
		productEntity.setPrice(PRICE);
		return productEntity;
	}
}