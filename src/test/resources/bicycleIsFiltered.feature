Feature: As a user I want to filter bicycles

  @regression
  Scenario: Bicycle should be filtered
    Given the user log in
    When the user filter bicycle with serial number "CC657601215"
    Then bicycle was filtered by serial number "CC657601215"