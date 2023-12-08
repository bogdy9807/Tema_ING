package com.example.ing.api;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.api.dto.ProductPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/product")
public interface ProductApi {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Finds all the products available")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Products were returned successfully"),
			@ApiResponse(responseCode = "500", description = "An exception occurred while fetching products")
	})
	ResponseEntity<List<ProductDto>> getProducts();

	@GetMapping(value = "/{productExtId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Finds the product with the specified external id",
			parameters = {
					@Parameter(in = ParameterIn.PATH, name = "productExtId", description = "Unique external identifier of the product")
			})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product was found and response was returned"),
			@ApiResponse(responseCode = "404", description = "Product was not found"),
			@ApiResponse(responseCode = "500", description = "An exception occurred while searching for the product")
	})
	ResponseEntity<ProductDto> getProduct(@PathVariable String productExtId);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Adds a new product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The addition of the product was done successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request data"),
			@ApiResponse(responseCode = "500", description = "An exception occurred during addition of the product")
	})
	ResponseEntity<String> addProduct(@RequestBody ProductDto productDto);

	@PatchMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Updates the price of a product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The update for product price was successful"),
			@ApiResponse(responseCode = "400", description = "Bad request data"),
			@ApiResponse(responseCode = "404", description = "Product with specified external id was not found"),
			@ApiResponse(responseCode = "500", description = "An exception occurred during product price update")
	})
	ResponseEntity<Void> updateProductPrice(@Valid @RequestBody ProductPriceDto productPriceDto);
}
