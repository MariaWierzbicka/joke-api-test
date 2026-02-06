Feature: Data structure verification

  Scenario Outline: Check data structure of multiple random jokes

    Given The url is set to get <number> random jokes
    When Request for multiple random jokes is sent
    Then Each response item contains correct fields:
      | type      |
      | setup     |
      | punchline |
      | id        |
    And Values are not empty
    And Response contains a correct number <number> of items
    Examples:
      | number |
      | 5      |
      | 2      |



