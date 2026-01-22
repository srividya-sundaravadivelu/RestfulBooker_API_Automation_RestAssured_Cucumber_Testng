package com.restfulbooker.context;

import java.util.List;

import com.restfulbooker.pojo.BookingRequest;

import io.restassured.response.Response;

public class TestContext {

	private Response response;
	private int bookingId;
	private List<Integer> bookingIds;
	private BookingRequest payload;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public List<Integer> getBookingIds() {
		return bookingIds;
	}

	public void setBookingIds(List<Integer> bookingIds) {
		this.bookingIds = bookingIds;
	}
	
	public BookingRequest getPayload() {
		return payload;
	}
	
	public void setPayload(BookingRequest payload) {
		this.payload = payload;
	}
}
