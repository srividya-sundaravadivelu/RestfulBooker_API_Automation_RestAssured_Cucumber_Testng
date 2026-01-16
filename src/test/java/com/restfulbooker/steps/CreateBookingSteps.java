package com.restfulbooker.steps;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.assertions.BookingAssertions;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.pojo.CreateBookingResponse;
import com.restfulbooker.utils.JsonUtils;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CreateBookingSteps {	

	private final TestContext context;
	private BookingRequest payload;

	public CreateBookingSteps(TestContext context) {
		this.context = context;
	}

	@When("I send a POST request to create booking with data {string}")
	public void i_send_a_post_request_to_create_booking_with_data(String filePath) {

		payload = JsonUtils.readJson("src/test/resources/testdata/" + filePath, BookingRequest.class);

		Response response = BookingApi.createBooking(payload);
		
		context.setResponse(response);
	}

	@Then("the booking should be created successfully")
	public void the_booking_should_be_created_successfully() {
		Response response = context.getResponse();
		response.then()
				.spec(ResponseSpecBuilderUtil.getBookingResponseSpec());

		int bookingId = response.jsonPath().getInt("bookingid");
		context.setBookingId(bookingId);
		System.out.println("Booking ID: " + bookingId);
		
		CreateBookingResponse bookingResponse = response.then().extract().as(CreateBookingResponse.class);	
		BookingAssertions.assertBookingMatches(payload, bookingResponse.getBooking());
	}
	
	@Then("the API should return status code {int}")
	public void the_api_should_return_status_code(int statusCode) {
		Response response = context.getResponse();
		response.then()
				.statusCode(statusCode);
	}

}