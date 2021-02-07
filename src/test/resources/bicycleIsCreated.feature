Feature: As a user I want to create bicycle

  Background:
  Given the user log in

  @smoke
  Scenario Outline: Bicycle should be created with a particular serial number
    When the user create bicycle with serial number "<sn>"
    Then bicycle with serial number "<sn>" was created
    Examples:
      | sn        |
      | B03496A455 |
      | B034896A88 |