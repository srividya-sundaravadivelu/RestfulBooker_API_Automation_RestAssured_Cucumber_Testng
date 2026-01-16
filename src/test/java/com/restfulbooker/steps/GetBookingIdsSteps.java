package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetBookingIdsSteps {

	private final TestContext context;

	public GetBookingIdsSteps(TestContext context) {
		this.context = context;
	}

	@When("I send a GET request to fetch all booking IDs")
	public void i_send_a_get_request_to_fetch_all_booking_i_ds() {
		Response response = BookingApi.getBookingIds();
		context.setResponse(response);
	}

	@Then("I should get a non-empty list of booking IDs")
	public void i_should_get_a_non_empty_list_of_booking_i_ds() {
		Response response = context.getResponse();
		response.then().spec(ResponseSpecBuilderUtil.getBookingIdsResponseSpec());
	}
}