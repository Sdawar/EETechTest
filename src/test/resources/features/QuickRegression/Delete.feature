Feature: Delete Booking
  In order to book a holiday
  As a customer
  I want to delete my existing booking


  Scenario: Delete Hotel Booking
    Given I started application as a Agent in chrome and go to hotel booking site
    And I enter user details on the hotel page
    When I click on delete button
    Then the booking for "DeleteUser" is deleted
