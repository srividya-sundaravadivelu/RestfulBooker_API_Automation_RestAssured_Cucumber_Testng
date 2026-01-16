@healthCheck
Feature: Health Check API

  As a consumer of Restful Booker API
  I want to verify the service health
  So that I can ensure the API is up and running

  Scenario: Verify Restful Booker API health status
    Given the Restful Booker API is available
    When I send a GET request to the health check endpoint
    Then the API health check should be successful
