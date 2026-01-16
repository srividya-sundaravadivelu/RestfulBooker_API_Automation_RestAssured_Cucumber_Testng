@createBooking
Feature: Create Booking API

  Scenario Outline: Create a new booking
    Given the Restful Booker API is available
    When I send a POST request to create booking with data "<bookingJson>"
    Then the booking should be created successfully 
    Examples:
      | bookingJson                       |
      | booking.json                       |

      
