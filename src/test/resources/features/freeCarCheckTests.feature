Feature: Free Car Checks

  Background:
    Given I read input file "car_input.txt"
    And I extract vehicle registration numbers

  @FrontendTest
  Scenario: Check Vehicle Identity
    Given I am on Free Car Check website
    When I obtain the vehicle identities for the registration numbers
    Then the obtained vehicle identities should match "car_output.txt"