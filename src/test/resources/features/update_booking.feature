@updateBooking 
Feature: Update Booking

  Background:
    Given I create a booking with data "booking.json"

  @fullUpdate
  Scenario: Update booking with valid data
    When I update the booking with data "updateBooking.json"
    Then the booking should be updated successfully

  @partialUpdate
  Scenario: Update booking with partial data
    When I partially update the booking with data "partialUpdateBooking.json"
    Then the booking should be partially updated
