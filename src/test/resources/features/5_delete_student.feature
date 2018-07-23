Feature: Delete a student
  Scenario: Deleting a student
    Given The student id is 3:
    When I make a DELETE call to "api/student/" endpoint
    Then response status code should be 204