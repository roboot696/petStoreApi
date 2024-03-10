Feature: Everything about pets

  Scenario: Add new pet to the store with correct information
    Given the user wants to add a new pet to the store
    When the user submits the correct information of the new pet
    Then the request is successfully


  Scenario Outline: find pet by status available
    Given As a System user
    When the user find a pet by status <status>
    Then the request is successfully
    Examples:
    |status|
    |available|
    |pending|
    |sold|


  Scenario Outline: find pet by invalid status
    Given As a System user
    When the user find a pet by status <status>
    Then the system returns invalid status value
    Examples:
      |status|
      |      |

  Scenario Outline: find pet by an existing tag
    Given As a System user
    When the user find a pet by tag <tag>
    Then the request is successfully
    Examples:
      |tag|
      |tag1|
      |tag2|
      |tag3|

  Scenario Outline: find pet by empty parameter value tag
    Given As a System user
    When the user find a pet by tag <tag>
    Then  the system returns invalid status value
    Examples:
      |tag|
      |   |

  Scenario Outline: find pet by Id valid
    Given As a System user
    When the user find a pet by Id <ID>
    Then  the request is successfully
    Examples:
      |ID|
      |10|

  Scenario Outline: find pet by Id valid
    Given As a System user
    When the user find a pet by Id <ID>
    Then pet not found message
    Examples:
      |ID|
      |1111111111|

  Scenario Outline: Delete a pet
    Given As a System user
    When the user wants to delete a pet by <petID>
    Then  the request is successfully
    Examples:
      |petID|
      |10|

  Scenario Outline: Update an existing pet by a valid ID
    Given As a System user
    When the user want to update a pet by ID <ID>
    Then  the request is successfully
    Examples:
      |ID|
      |1 |
  Scenario Outline: Update an existing pet with not valid ID
    Given As a System user
    When the user want to update a pet by ID <ID>
    Then pet not found message
    Examples:
    |ID|
    |10 |

  Scenario Outline: Update a pet in the store with form data
    Given As a System user
    When the user want to update a pet by ID, name and status <petID> <newName> <status>
    Then the request is successfully
    Examples:
      |petID|newName|status|
      |1 | mocca    |available|
