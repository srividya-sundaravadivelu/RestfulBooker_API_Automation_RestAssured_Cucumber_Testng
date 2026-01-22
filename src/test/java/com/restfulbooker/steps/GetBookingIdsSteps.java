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

	@When("I retrieve all booking IDs")
	public void i_retrieve_all_booking_i_ds() {
		Response response = BookingApi.getBookingIds();
		context.setResponse(response);
	}

	@Then("I should get a non-empty list of booking IDs")
	public void i_should_get_a_non_empty_list_of_booking_i_ds() {
		Response response = context.getResponse();
		response.then().spec(ResponseSpecBuilderUtil.getBookingIdsResponseSpec());
	}
}