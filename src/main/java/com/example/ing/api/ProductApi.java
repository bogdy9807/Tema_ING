package com.example.ing.api;

import com.example.ing.api.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/product")
public interface ProductApi {

	@GetMapping(value = "/{productExtId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDto> getProduct(@PathVariable String productExtId);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> addProduct(@RequestBody ProductDto productDto);
}
