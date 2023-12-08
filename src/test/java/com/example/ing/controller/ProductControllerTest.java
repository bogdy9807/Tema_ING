package com.example.ing.controller;

import com.example.ing.api.dto.ProductDto;
import com.example.ing.api.dto.ProductPriceDto;
import com.example.ing.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	private static final String PRODUCT_URL = "/product";
	private static final String PRICE_PATH = "/price";
	private static final String EXTERNAL_ID = "extId";
	private static final float PRICE = 10F;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final String NAME = "name";

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void whenGetProducts_expectStatus200() throws Exception {
		mockMvc.perform(get(PRODUCT_URL))
				.andExpect(status().isOk());
	}

	@Test
	void whenGetProduct_expectStatus200() throws Exception {
		mockMvc.perform(get(PRODUCT_URL + "/test"))
				.andExpect(status().isOk());
	}

	@Test
	void whenUpdatePrice_expectStatus200() throws Exception {
		ProductPriceDto productPriceDto = ProductPriceDto.builder()
				.price(PRICE)
				.externalId(EXTERNAL_ID)
				.build();

		mockMvc.perform(patch(PRODUCT_URL + PRICE_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(MAPPER.writeValueAsString(productPriceDto)))
				.andExpect(status().isOk());
	}

	@Test
	void whenUpdatePriceAndExternalIdIsBlank_expectStatus400() throws Exception {
		ProductPriceDto productPriceDto = ProductPriceDto.builder()
				.price(PRICE)
				.externalId(EMPTY)
				.build();

		mockMvc.perform(patch(PRODUCT_URL + PRICE_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(MAPPER.writeValueAsString(productPriceDto)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void whenUpdatePriceAndPriceIsNegative_expectStatus400() throws Exception {
		ProductPriceDto productPriceDto = ProductPriceDto.builder()
				.price(-10F)
				.externalId(EXTERNAL_ID)
				.build();

		mockMvc.perform(patch(PRODUCT_URL + PRICE_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(MAPPER.writeValueAsString(productPriceDto)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void whenAddProduct_expectStatus201() throws Exception {
		ProductDto productDto = ProductDto.builder()
				.name(NAME)
				.price(PRICE)
				.build();

		mockMvc.perform(post(PRODUCT_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(MAPPER.writeValueAsString(productDto)))
				.andExpect(status().isCreated());
	}

	@Test
	void whenAddProductAndNameIsEmpty_expectStatus400() throws Exception {
		ProductDto productDto = ProductDto.builder()
				.price(PRICE)
				.build();

		mockMvc.perform(post(PRODUCT_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(MAPPER.writeValueAsString(productDto)))
				.andExpect(status().isBadRequest());
	}
}