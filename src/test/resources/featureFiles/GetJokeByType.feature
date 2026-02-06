Feature: Get joke by type

  Background:
    Given The Base URI is set

  Scenario Outline: Retrieve a random joke by specific type

    Given The url is set to a specific joke type <type>
    When Request containing a specific type is sent
    Then Status code should be 200
    And Verify the joke contains correct type value
    Examples:
      | type        |
      | general     |
      | programming |
      | dad         |
      | knock-knock |
