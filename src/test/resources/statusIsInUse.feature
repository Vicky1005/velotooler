Feature: As a user I want to make sure bicycle status is In Use

  @regression
  Scenario: Bicycle should has status In Use
    Given the user log in
    When the user sets status filter with serial number "10LHGB6745"
    Then bicycle with serial number "10LHGB6745" has status In Use