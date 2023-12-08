package com.example.ing.controller;

import com.example.ing.api.ProductApi;
import com.example.ing.api.dto.ProductDto;
import com.example.ing.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final ProductService productService;

	@Override
	public ResponseEntity<ProductDto> getProduct(String productExtId) {
		return ResponseEntity.ok(productService.getProduct(productExtId));
	}

	@Override
	public ResponseEntity<String> addProduct(ProductDto productDto) {
		return ResponseEntity.ok(productService.addProduct(productDto));
	}
}
