package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.assertions.BookingAssertions;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.factory.TestDataFactory;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UpdateBookingSteps {

	private final TestContext context;

	public UpdateBookingSteps(TestContext context) {
		this.context = context;
	}

	@Then("the booking should be updated successfully")
	public void the_booking_should_be_updated_successfully() {
		BookingRequest payload = context.getPayload();
		Response response = context.getResponse();
		response
		.then()
		.spec(ResponseSpecBuilderUtil.getBookingResponseByIdSpec());
		
		BookingRequest booking = response.then().extract().as(BookingRequest.class);	
		BookingAssertions.assertBookingMatches(payload, booking);
	}
	
	@When("I partially update the booking")
	public void i_partially_update_the_booking() {
		BookingRequest payload = TestDataFactory.partialUpdateBooking();
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