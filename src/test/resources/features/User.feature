Feature: Operation about user

  Scenario: Create user
    Given a system user
    When the user send the information with a POST request
    Then the request is sent successfully

  Scenario Outline: Logs user into the system
    Given a system user
    When the user sends his correct <username> and <password> information
    Then the request is sent successfully
    Examples:
      | username | password |
      | theUser    | 123459    |

  Scenario: Logs out current logged in user session
    Given a system user
    When the user logs out
    Then the system confirm the user is logged out

  Scenario Outline: Get user by username
    Given a system user
    When a request is made to get the user with an <username>
    Then the request is sent successfully

    Examples:

      | username |
      | theUser   |

  Scenario Outline: Find a user with not existing username
    Given a system user
    When a request is made to get the user with an <username>
    Then the system returns user error message

    Examples:

      | username  |
      | theUserxxx|


  Scenario Outline: Delete user
    Given a system user
    When a request is made to delete the user with <username>
    Then the request is sent successfully
    Examples:

      | username |
      | user15    |