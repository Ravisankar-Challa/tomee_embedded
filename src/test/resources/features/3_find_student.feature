Feature: Find a student
  Scenario: Finding a student
    Given The student id is 2:
    When I make a GET call to "api/student/" endpoint
    Then response status code should be 200
    And response content type should be "application/json"
    And response should be json:
    """
    {
	    "id": "${json-unit.ignore}",
	    "name": "Sankar"
	}
	"""