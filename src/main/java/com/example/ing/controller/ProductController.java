package com.example.ing.controller;

import com.example.ing.api.ProductApi;
import com.example.ing.api.dto.ProductDto;
import com.example.ing.api.dto.ProductPriceDto;
import com.example.ing.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final ProductService productService;

	@Override
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@Override
	public ResponseEntity<ProductDto> getProduct(String productExtId) {
		return ResponseEntity.ok(productService.getProduct(productExtId));
	}

	@Override
	public ResponseEntity<String> addProduct(ProductDto productDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto));
	}

	@Override
	public ResponseEntity<Void> updateProductPrice(@Valid ProductPriceDto productPriceDto) {
		productService.updateProductPrice(productPriceDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
