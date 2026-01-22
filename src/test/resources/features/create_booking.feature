@createBooking
Feature: Create Booking API

  
  Scenario: Create a new booking
    Given the Restful Booker API is available
    When I create a booking with valid details
    Then the booking should be created successfully    
    
	@negative
	Scenario Outline: Create booking with invalid payload
	  Given the Restful Booker API is available
	  When I remove "<field>" from the booking payload and create booking
	  Then the API should return error

		Examples:
		  | field          |
		  | firstname      |
		  | lastname       |
		  | bookingdates   |

      
