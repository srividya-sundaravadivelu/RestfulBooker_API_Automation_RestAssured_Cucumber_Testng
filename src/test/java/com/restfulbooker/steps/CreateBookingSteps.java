package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.assertions.BookingAssertions;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.factory.TestDataFactory;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.pojo.CreateBookingResponse;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CreateBookingSteps {

	private final TestContext context;

	public CreateBookingSteps(TestContext context) {
		this.context = context;
	}

	@When("I create a booking with valid details")
	public void i_create_a_booking_with_valid_details() {

		BookingRequest payload = TestDataFactory.createValidBooking();
		context.setPayload(payload);

		Response response = BookingApi.createBooking(payload);

		context.setResponse(response);
		int bookingId = response.jsonPath().getInt("bookingid");
		context.setBookingId(bookingId);
	}

	@Then("the booking should be created successfully")
	public void the_booking_should_be_created_successfully() {
		Response response = context.getResponse();
		response.then().spec(ResponseSpecBuilderUtil.getBookingResponseSpec());

		int bookingId = response.jsonPath().getInt("bookingid");
		context.setBookingId(bookingId);
		System.out.println("Booking ID: " + bookingId);

		CreateBookingResponse bookingResponse = response.then().extract().as(CreateBookingResponse.class);
		BookingAssertions.assertBookingMatches(context.getPayload(), bookingResponse.getBooking());
	}

	@Then("the API should return error")
	public void the_api_should_return_error() {
		Response response = context.getResponse();
		response.then().statusCode(500);
	}

	@When("I remove {string} from the booking payload and create booking")
	public void i_remove_from_the_booking_payload_and_create_booking(String field) {
		BookingRequest payload = TestDataFactory.createBookingWithMissingField(field);

		Response response = BookingApi.createBooking(payload);
		context.setResponse(response);
	}

}