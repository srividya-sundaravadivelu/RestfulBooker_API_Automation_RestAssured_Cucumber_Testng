@getBooking
Feature: Get Booking by ID or by name

  Background:
    Given I create a booking with data "booking.json"

	@getBookingByID
  Scenario: Retrieve booking by ID
    When I send a GET request for booking by ID
    Then the response should return the correct booking details
    
   
  @getBookingByName
  Scenario Outline: Retrieve booking IDs filtered by name
    When I send a GET request to fetch booking IDs by firstname "<firstname>" and lastname "<lastname>"
    Then I should get a list of booking IDs matching the name

    Examples:
      | firstname | lastname |
      | John      | Doe      |
 
