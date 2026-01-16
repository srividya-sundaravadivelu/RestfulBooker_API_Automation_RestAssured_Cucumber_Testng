package com.restfulbooker.factory;

import com.restfulbooker.utils.ConfigReader;
import com.restfulbooker.utils.TokenManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestFactory {

	private RequestFactory() {
	}

	public static RequestSpecification getRequest() {
		return RestAssured.given()
				.baseUri(ConfigReader.getBaseUri())
				.contentType("application/json")
				.accept("application/json");

	}

	public static RequestSpecification getAuthenticatedRequest() {
		return getRequest()
				.cookie("token", TokenManager.getToken());
	}
}
