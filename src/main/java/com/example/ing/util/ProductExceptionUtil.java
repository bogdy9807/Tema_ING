package com.example.ing.util;

import com.example.ing.service.exception.ProductAlreadyPresentException;
import com.example.ing.service.exception.ProductNotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductExceptionUtil {

	public static final String EXTERNAL_ID = "external Id";
	public static final String NAME = "name";
	private static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product with external id %s was not found";
	private static final String PRODUCT_EXISTS_MESSAGE_FORMAT = "Product with %s %s already exists";

	public static ProductNotFoundException getProductNotFoundException(String externalId) {
		String exceptionMessage = String.format(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, externalId);
		return new ProductNotFoundException(exceptionMessage);
	}

	public static void throwProductAlreadyPresentException(String field, String value) {
		String exceptionMessage = String.format(PRODUCT_EXISTS_MESSAGE_FORMAT, field, value);
		throw new ProductAlreadyPresentException(exceptionMessage);
	}
}
