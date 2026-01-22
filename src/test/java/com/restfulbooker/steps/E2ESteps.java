package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.factory.TestDataFactory;
import com.restfulbooker.pojo.BookingRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class E2ESteps {

	private final TestContext context;

	public E2ESteps(TestContext context) {
		this.context = context;
	}

	@Given("a booking exists")
	public void a_booking_exists() {
		BookingRequest payload = TestDataFactory.createValidBooking();

		Response response = BookingApi.createBooking(payload);

		int bookingId = response.jsonPath().getInt("bookingid");
		context.setBookingId(bookingId);
	}

	@When("I retrieve the booking")
	public void i_retrieve_the_booking() {
		int bookingId = context.getBookingId();

		Response response = BookingApi.getBooking(bookingId);

		context.setResponse(response);

	}

	@When("I update the booking details")
	public void i_update_the_booking() {
		BookingRequest updateBookingPayload = TestDataFactory.validUpdateBooking();
		context.setPayload(updateBookingPayload);

		Response response = BookingApi.updateBooking(updateBookingPayload, context.getBookingId());

		context.setResponse(response);
	}

	@When("I delete the booking")
	public void i_delete_the_booking() {
		int bookingId = context.getBookingId();
		if (bookingId > 0) {
			System.out.println("Deleting booking with ID: " + bookingId);
			BookingApi.deleteBooking(bookingId);
		}
	}

	@Then("the booking should no longer exist")
	public void booking_should_not_exist() {
		int bookingId = context.getBookingId();
		Response response = BookingApi.getBooking(bookingId);
		response.then().statusCode(404);
		context.setBookingId(0);
	}
}