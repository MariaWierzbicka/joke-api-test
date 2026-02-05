Feature: Data structure verification

  Scenario Outline: Check data structure of multiple random jokes
    Given Joke API is responsive
    When Request for <number> random jokes is sent
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



