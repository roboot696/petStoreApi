Feature: Access to Pet store orders

  Scenario: Place an order for a pet
    Given As a System user
    When the user sends a request to place the order
    Then the order for the pet is successfully placed

    Scenario Outline: Find purchase order by ID created
      Given As a System user
      When the user requests to find the purchase order by <ID>
      Then the system returns the purchase order details
      Examples:
       | ID  |
       | 10  |

  Scenario Outline: Find purchase order by ID no created
    Given As a System user
    When the user requests to find the purchase order by <ID>
    Then the system returns error message
    Examples:
      | ID  |
      | 100  |

  Scenario Outline: Delete purchase order by existing ID
    Given As a System user
    When the user requests send a request to delete the purchase order by <ID>
    Then the purchase order was deleted
    Examples:
      | ID  |
      | 100  |

  Scenario Outline: Delete purchase order by not existing ID
    Given As a System user
    When the user requests send a request to delete the purchase order by <ID>
    Then the system returns error message
    Examples:
      | ID  |
      | 2000|