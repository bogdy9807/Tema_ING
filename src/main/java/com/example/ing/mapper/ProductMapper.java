package com.example.ing.mapper;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.entity.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

	public static ProductDto mapToProductDto(ProductEntity productEntity) {
		return ProductDto.builder()
				.name(productEntity.getName())
				.externalId(productEntity.getExternalId())
				.price(productEntity.getPrice())
				.build();
	}

	public static ProductEntity mapToProductEntity(ProductDto productDto) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(productDto.getName());
		productEntity.setExternalId(productDto.getExternalId());
		productEntity.setPrice(productDto.getPrice());
		return productEntity;
	}
}
