package com.restfulbooker.assertions;

import com.restfulbooker.pojo.BookingRequest;
import org.testng.Assert;

public class BookingAssertions {

    public static void assertBookingMatches(
            BookingRequest expected,
            BookingRequest actual) {

        Assert.assertNotNull(actual, "Booking response should not be null");

        Assert.assertEquals(
                actual.getFirstname(),
                expected.getFirstname(),
                "Firstname mismatch"
        );

        Assert.assertEquals(
                actual.getLastname(),
                expected.getLastname(),
                "Lastname mismatch"
        );

        Assert.assertEquals(
                actual.getBookingdates().getCheckin(),
                expected.getBookingdates().getCheckin(),
                "Check-in date mismatch"
        );

        Assert.assertEquals(
                actual.getBookingdates().getCheckout(),
                expected.getBookingdates().getCheckout(),
                "Check-out date mismatch"
        );

        Assert.assertEquals(
                actual.getAdditionalneeds(),
                expected.getAdditionalneeds(),
                "Additional needs mismatch"
        );

        Assert.assertEquals(
                actual.isDepositpaid(),
                expected.isDepositpaid(),
                "Deposit paid mismatch"
        );

        Assert.assertEquals(
                actual.getTotalprice(),
                expected.getTotalprice(),
                "Total price mismatch"
        );
    }
}
