Feature: As a user I want to delete all bicycles

  @regression
  Scenario: All bicycles should be deleted
    Given the user log in
    When the user delete all bicycles
    Then all bicycles were deleted