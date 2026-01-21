package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class HealthCheckSteps {

	private final TestContext context;

	public HealthCheckSteps(TestContext context) {
		this.context = context;
	}

	@Given("the Restful Booker API is available")
	public void the_restful_booker_api_is_available() {		
	}

	@When("I send a GET request to the health check endpoint")
	public void i_send_a_get_request_to_the_health_check_endpoint() {
		Response response = BookingApi.ping();
		context.setResponse(response);
	}

	@Then("the API health check should be successful")
	public void the_api_health_check_should_be_successful() {
		context.getResponse().then().spec(ResponseSpecBuilderUtil.getCreatedResponseSpec());

	}
}
