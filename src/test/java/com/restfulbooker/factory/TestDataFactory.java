package com.restfulbooker.factory;

import com.restfulbooker.pojo.AuthRequest;
import com.restfulbooker.pojo.BookingRequest;
import com.restfulbooker.utils.JsonUtils;

public class TestDataFactory {

	private TestDataFactory() {
	}

	public static BookingRequest createValidBooking() {
		return JsonUtils.readJsonFromResources("booking.json", BookingRequest.class);
	}

	public static BookingRequest createBookingWithMissingField(String field) {

		BookingRequest payload = JsonUtils.readJsonFromResources("booking.json", BookingRequest.class);

		switch (field.toLowerCase()) {
		case "firstname":
			payload.setFirstname(null);
			break;

		case "lastname":
			payload.setLastname(null);
			break;

		case "bookingdates":
			payload.setBookingdates(null);
			break;

		default:
			throw new IllegalArgumentException("Unsupported field removal: " + field);
		}

		return payload;
	}

	public static BookingRequest validUpdateBooking() {
		return JsonUtils.readJsonFromResources("updateBooking.json", BookingRequest.class);
	}

	public static BookingRequest createBookingWithName(String firstname, String lastname) {

		BookingRequest payload = JsonUtils.readJsonFromResources("booking.json", BookingRequest.class);

		payload.setFirstname(firstname);
		payload.setLastname(lastname);

		return payload;
	}

	public static BookingRequest partialUpdateBooking() {
		return JsonUtils.readJsonFromResources("partialUpdateBooking.json", BookingRequest.class);
	}

	public static AuthRequest validAuthPayload() {
		return JsonUtils.readJsonFromResources("auth.json", AuthRequest.class);
	}

}
