package com.restfulbooker.factory;

import com.restfulbooker.utils.ConfigReader;
import com.restfulbooker.utils.TokenManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RequestFactory {

	private RequestFactory() {
	}

	public static RequestSpecification getRequest() {
		return RestAssured.given()
				.filter(new AllureRestAssured())
				.log().ifValidationFails(LogDetail.ALL)
				.baseUri(ConfigReader.getBaseUri())
				.contentType("application/json")
				.accept("application/json");

	}

	public static RequestSpecification getAuthenticatedRequest() {
		return getRequest()
				.cookie("token", TokenManager.getToken());
	}
}
