Feature: Find all students
  Scenario: Find all students
    When I make a GET call to find all students to  "api/students" endpoint
    Then response status code should be 200
    And response content type should be "application/json"
    And response should be json:
    """
    [{
	    "id": "${json-unit.ignore}",
	    "name": "Ravi"
	 },
	{
	    "id": "${json-unit.ignore}",
	    "name": "Sankar"
	}]
	"""
