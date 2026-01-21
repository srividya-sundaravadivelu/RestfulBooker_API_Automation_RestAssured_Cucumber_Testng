@createBooking
Feature: Create Booking API

  
  Scenario: Create a new booking
    Given the Restful Booker API is available
    When I send a POST request to create booking with data "booking.json"
    Then the booking should be created successfully    
    
	@negative
	Scenario Outline: Create booking with invalid payload
	  Given the Restful Booker API is available
	  When I remove "<field>" from the booking payload "booking.json" and send POST request to create booking
	  Then the API should return status code 500

		Examples:
		  | field          |
		  | firstname      |
		  | lastname       |
		  | bookingdates   |

      
