@getBookingIds
Feature: Get Booking IDs

  Background:
    Given the Restful Booker API is available

  Scenario: Retrieve all booking IDs
    When I send a GET request to fetch all booking IDs
    Then I should get a non-empty list of booking IDs


