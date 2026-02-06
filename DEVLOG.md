# DEVLOG

## Stage 1 - Project initialization
* Created a project, researched the most fitting approach
* Created `pom.xml` with dependencies.
* Added `TestRunner` class using standard JUnit 4 `@RunWith(Cucumber.class)` annotation.
* configured Git repository and .gitignore file


## Stage 2 - Architecture refactoring, data verification
* Added project structure, separating logic into BaseSteps, CommonSteps and JokeSteps to avoid code duplication
* Implemented `TestContext` class, to make sure the request specification and response are passed correctly between classes. (used AI in this step to ensure correct implementation and to better understand dependency injections)
* **Feature:** Added Content Verification steps.
* **Feature:** Implemented Data Structure verification to ensure the API returns the correct JSON schema (id, type, setup, punchline) regardless of specific values.
* Faced an issue where example joke id was sometimes treated as `int` and other times as `String`. Handled this issue by directly defining the scenario Examples as strings.

## Stage 3 - Feature expansion, refactoring and documentation
* **Feature:** Implemented "Retrieve by Type" feature.
* Refactored the URL building code to allow handling dynamic URL parameters for joke types (e.g., `/jokes/programming/random`).
* **Feature:** Added generic status code verification step (`@Then("The status code should be {int}")`) and implemented it across different test scenarios.
* **Documentation:** Added `README.md` and `DEVLOG.md` to project root.
