package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.assertions.BookingAssertions;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.utils.JsonUtils;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UpdateBookingSteps {

	private final TestContext context;
	private BookingRequest updateBookingPayload;

	public UpdateBookingSteps(TestContext context) {
		this.context = context;
	}

	@When("I update the booking with data {string}")
	public void i_update_the_booking_with_data(String filePath) {
		updateBookingPayload = JsonUtils.readJson("src/test/resources/testdata/" + filePath, BookingRequest.class);

		Response response = BookingApi.updateBooking(updateBookingPayload, context.getBookingId());
		
		context.setResponse(response);
	}

	@Then("the booking should be updated successfully")
	public void the_booking_should_be_updated_successfully() {
		Response response = context.getResponse();
		response
		.then()
		.spec(ResponseSpecBuilderUtil.getBookingResponseByIdSpec());
		
		BookingRequest booking = response.then().extract().as(BookingRequest.class);	
		BookingAssertions.assertBookingMatches(updateBookingPayload, booking);
	}
	
	@When("I partially update the booking with data {string}")
	public void i_partially_update_the_booking_with_data(String filePath) {
		BookingRequest payload = JsonUtils.readJson("src/test/resources/testdata/" + filePath, BookingRequest.class);

		Response response = BookingApi.partialUpdateBooking(payload, context.getBookingId());
		
		context.setResponse(response);
	}
	
	@Then("the booking should be partially updated")
	public void the_booking_should_be_partially_updated() {
		Response response = context.getResponse();
		response
		.then()
		.spec(ResponseSpecBuilderUtil.getBookingResponseByIdSpec());
	}
}