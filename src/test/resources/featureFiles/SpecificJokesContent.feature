Feature: Specific jokes' content

  Background:
    Given The Base URI is set

  Scenario Outline:
    Given The url is set to a specific joke id <id>
    When Request containing joke id is sent
    Then Verify correctness of values: <id>, <type>, <setup>, <punchline>
    Examples:
      | id    | type          | setup                                                      | punchline                                 |
      | "312" | "general"     | "Why did Dracula lie in the wrong coffin?"                 | "He made a grave mistake."                |
      | "266" | "general"     | "What's brown and sticky?"                                 | "A stick."                                |
      | "384" | "dad"         | "Why don't eggs tell jokes?"                               | "Because they would crack each other up." |
      | "12"  | "knock-knock" | "Knock knock. \n Who's there? \n Cows go. \n Cows go who?" | "No, cows go moo."                        |
      | "127" | "general"     | "How do you make a hankie dance?"                          | "Put a little boogie in it."              |

