Feature: update a student
  Scenario: update a student details
    Given The student details are as below:
    |   id   |      name      |
    |   1    |     Steven     |
    When I make a PUT call to "api/student" endpoint
    Then response status code should be 204