Feature: As a user I want to duplicate bicycle

  @regression
  Scenario: Bicycle should be duplicated
    Given the user log in
    When the user duplicate bicycle with serial number "SK457600915"
    Then bicycle was duplicated