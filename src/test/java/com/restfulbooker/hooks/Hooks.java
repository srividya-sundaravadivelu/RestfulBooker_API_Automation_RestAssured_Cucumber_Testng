package com.restfulbooker.hooks;

import com.restfulbooker.api.BookingApi;
import com.restfulbooker.context.TestContext;
import com.restfulbooker.utils.ResponseSpecBuilderUtil;
import com.restfulbooker.utils.TokenManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	private final TestContext context;

	public Hooks(TestContext context) {
		this.context = context;
	}

	@Before(order = 0)
	public void setup() {
		// Generate token once before scenarios
		TokenManager.getToken();
	}

	@After
	public void tearDown() {

		// clean up any created bookings
		int bookingId = context.getBookingId();
		if (bookingId > 0) {
			System.out.println("Deleting booking with ID: " + bookingId);
			BookingApi.deleteBooking(bookingId)
				.then()
				.spec(ResponseSpecBuilderUtil.getCreatedResponseSpec());
		}
	}
}
