Feature: CreateBooking
  In order to book a holiday
  As a customer
  I want to add a new booking into the booking form


  Scenario: CreateHotelBooking
    Given I started application as a Agent in chrome and go to hotel booking site
    And I check how many rows are returned
    And I enter user details for booking
    When I click on save button
    Then booking row should have increased by one

