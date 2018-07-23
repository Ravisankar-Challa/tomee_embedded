Feature: Add a student
  Scenario: Adding a new student
    Given The student details are as below:
    |     name     |
    |     Shalini  |
    When I make a POST call to "api/student" endpoint
    Then response status code should be 201