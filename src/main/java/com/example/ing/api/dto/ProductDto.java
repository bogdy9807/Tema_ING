package com.example.ing.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProductDto {

	@NotBlank
	@Schema(description = "Unique name of the product, should be unique")
	private String name;

	@Schema(description = "Unique external id of the product, should be unique")
	private String externalId;

	@PositiveOrZero
	@Schema(description = "Price of the product, should be positive")
	private Float price;
}
