@createBooking
Feature: Create Booking API

  Scenario Outline: Create a new booking
    Given the Restful Booker API is available
    When I send a POST request to create booking with data "<payload>"
    Then the booking should be created successfully 
    Examples:
      | payload                            |
      | booking.json                       |
      
  @negative  
  Scenario Outline: Create booking with invalid payload
  	Given the Restful Booker API is available
	  When I send a POST request to create booking with data "<payload>"
	  Then the API should return status code 500

		Examples:
		  | payload                    |
		  | missing_firstname.json     |
		  | missing_lastname.json      |
		  | missing_bookingdates.json  |

      
