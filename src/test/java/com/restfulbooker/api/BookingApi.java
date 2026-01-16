package com.restfulbooker.api;

import com.restfulbooker.pojo.AuthRequest;
import com.restfulbooker.pojo.BookingRequest;
import io.restassured.response.Response;

import static com.restfulbooker.factory.RequestFactory.getAuthenticatedRequest;
import static com.restfulbooker.factory.RequestFactory.getRequest;

public class BookingApi {

	public static Response createBooking(BookingRequest payload) {
		return getAuthenticatedRequest()
				.body(payload)
				.when()
				.post("/booking");
	}

	public static Response updateBooking(BookingRequest payload, int bookingId) {
		return getAuthenticatedRequest()
				.pathParam("id", bookingId)
				.body(payload)
				.when()
				.put("/booking/{id}");
	}
	
	public static Response partialUpdateBooking(BookingRequest payload, int bookingId) {
		return getAuthenticatedRequest()
				.pathParam("id", bookingId)
	            .body(payload)
	            .when()
	            .patch("/booking/{id}");
	}
	
	public static Response getBookingIds() {
		return getAuthenticatedRequest()
				.when()
				.get("/booking");
	}
	
	
	public static Response getBooking(int bookingId)
	{
		return getAuthenticatedRequest()
				.pathParam("id", bookingId)
				.when()
				.get("/booking/{id}");
	}
	
	public static Response getBooking(String firstname, String lastname)
	{
		return getAuthenticatedRequest()
			.queryParam("firstname", firstname)
			.queryParam("lastname", lastname)
			.when()
			.get("/booking");
	}
	
	public static Response ping() {
		return getRequest()
				.when()
				.get("/ping");
	}
	
	public static Response deleteBooking(int bookingId) {
		return getAuthenticatedRequest()
				.pathParam("id", bookingId)
				.when()
				.delete("/booking/{id}");
	}
	
	public static Response createToken(AuthRequest authPayload) {
		 return getRequest()
			.body(authPayload)
			.when()
			.post("/auth");
	}
}
