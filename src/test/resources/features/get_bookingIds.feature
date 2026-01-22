@getBookingIds
Feature: Get Booking IDs 

  Scenario: Retrieve all booking IDs
    Given the Restful Booker API is available
    When I retrieve all booking IDs
    Then I should get a non-empty list of booking IDs


