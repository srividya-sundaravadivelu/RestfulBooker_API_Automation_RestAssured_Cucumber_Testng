package com.restfulbooker.utils;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.pojo.AuthRequest;

import io.restassured.response.Response;

public class TokenManager {

	private static String token;

	private TokenManager() {
		// Prevent object creation
	}

	public static String getToken() {
		if (token == null) {
			token = generateToken();
		}
		return token;
	}

	private static String generateToken() {
		AuthRequest authPayload = JsonUtils.readJson("src/test/resources/testdata/auth.json", AuthRequest.class);

		Response response = BookingApi.createToken(authPayload);

		return response
				.then()
				.extract()
				.path("token");
	}
}
