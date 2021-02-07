Feature: As a user I want to create service request

  @smoke
  Scenario: Service request should be created
    Given the user log in
    When the user create service request for bicycle "008490PHP5"
    Then service request was created