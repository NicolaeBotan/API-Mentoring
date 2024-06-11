Feature: PetStore API

  Scenario: List Pets API
    Given User has PetStore endpoint
    When  User sends Get request to list Pets
    Then  Status Code is 200
    And   Response contains List of Pets

    #NEGATIVE SCENARIO with non-existing pet id
    #NEGATIVE SCENARIO with invalid pet id
    #As many rows I have in 'Examples' Table, that many times the same Scenario will be executed
    # with the data that I Have in the table which is Parameterized
  Scenario Outline: GET non-existing pet
    Given User has PetStore endpoint
    When User sends GET request to list of <petIdType> pet by id
    Then Status Code is 404
    And Error message is '<errorMessage>'

    Examples:
      | petIdType    | errorMessage                                                     |
      | non-existing | Pet not found                                                    |
      | invalid      | java.lang.NumberFormatException: For input string: \"477839e45\" |



