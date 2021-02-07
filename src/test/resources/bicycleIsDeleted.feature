Feature: As a user I want to delete bicycle

  @regression
  Scenario: Bicycle should be deleted
    Given the user log in
    When the user delete bicycle with serial number "BCC6579915"
    Then bicycle with serial number "BCC6579915" was deleted