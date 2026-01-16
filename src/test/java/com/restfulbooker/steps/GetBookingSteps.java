package com.restfulbooker.steps;

import java.util.List;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.assertions.BookingAssertions;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.factory.RequestFactory;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.utils.JsonUtils;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetBookingSteps {

	private final TestContext context;
	private BookingRequest payload;
	private String expectedFirstname;
	private String expectedLastname;

	public GetBookingSteps(TestContext context) {
		this.context = context;
	}

	@Given("I create a booking with data {string}")
	public void i_create_a_booking_with_data(String filePath) {

		payload = JsonUtils.readJson("src/test/resources/testdata/" + filePath, BookingRequest.class);

		Response response = BookingApi.createBooking(payload);

		int bookingId = response.jsonPath().getInt("bookingid");
		context.setBookingId(bookingId);
	}

	@When("I send a GET request for booking by ID")
	public void i_send_a_get_request_for_booking_by_id() {
		int bookingId = context.getBookingId();

		Response response = BookingApi.getBooking(bookingId);

		context.setResponse(response);

	}

	@Then("the response should return the correct booking details")
	public void the_response_should_return_the_correct_booking_details() {

		Response response = context.getResponse();
		
		response
		.then()
		.spec(ResponseSpecBuilderUtil.getBookingResponseByIdSpec());

		BookingRequest booking = response.then().extract().as(BookingRequest.class);	
		BookingAssertions.assertBookingMatches(payload, booking);
	}

	@When("I send a GET request to fetch booking IDs by firstname {string} and lastname {string}")
	public void i_send_a_get_request_to_fetch_booking_i_ds_by_firstname_and_lastname(String firstname,
			String lastname) {
		
		expectedFirstname = firstname;
		expectedLastname = lastname;
		Response response = BookingApi.getBooking(firstname, lastname);
		context.setResponse(response);
	}

	@Then("I should get a list of booking IDs matching the name")
	public void i_should_get_a_list_of_booking_i_ds_matching_the_name() {

		Response response = context.getResponse();

		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		assert bookingIds != null && !bookingIds.isEmpty() : "No booking IDs found";

		// Loop over each bookingId and validate firstname/lastname
		for (Integer bookingId : bookingIds) {
			BookingRequest booking = RequestFactory.getAuthenticatedRequest()
					.pathParam("id", bookingId)
					.when()
					.get("/booking/{id}")
					.then()
					.statusCode(200)
					.extract().as(BookingRequest.class);

			assert booking.getFirstname().equals(expectedFirstname) : "Firstname mismatch for bookingId " + bookingId;
			assert booking.getLastname().equals(expectedLastname) : "Lastname mismatch for bookingId " + bookingId;
		}
	}

}