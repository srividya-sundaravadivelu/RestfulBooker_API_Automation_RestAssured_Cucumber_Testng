@updateBooking 
Feature: Update Booking

  @fullUpdate
  Scenario: Update booking with valid data
    Given a booking exists
    When I update the booking details
    Then the booking should be updated successfully

  @partialUpdate
  Scenario: Update booking with partial data
    Given a booking exists
    When I partially update the booking
    Then the booking should be partially updated
