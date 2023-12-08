package com.example.ing.api;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.api.dto.ProductPriceDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/product")
public interface ProductApi {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ProductDto>> getProducts();

	@GetMapping(value = "/{productExtId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDto> getProduct(@PathVariable String productExtId);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> addProduct(@RequestBody ProductDto productDto);

	@PatchMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> updateProductPrice(@RequestBody ProductPriceDto productPriceDto);
}
