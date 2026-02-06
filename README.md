# Official Joke API Test Automation Framework

A simple test automation framework for the [Official Joke API](https://github.com/15Dkatz/official_joke_api/) built using **Java**, **Cucumber**, **RestAssured**, and **Maven**.

## Tech Stack
* **Language:** Java 17
* **Build Tool:** Maven
* **BDD Framework:** Cucumber 7 (running on JUnit 4)
* **HTTP Client:** RestAssured 5.4.0
* **Assertions:** AssertJ 3.25.1 (Fluent Assertions)

## Project Structure
* `src/test/java/base` - Contains `BaseSteps` - base class for step implementation classes, contains shared utilities.
* `src/test/java/steps` - **Step Definitions**: `JokeSteps`, `CommonSteps`.
* `src/test/resources/featureFiles` - **Gherkin Feature Files**.
* `src/test/java/runner` - **Test Runner**: JUnit 4 runner class (`TestRunner.java`).
* `src/test/java/context` - **Test Context**: `TestContext.java` used for sharing state between different steps.
##  How to Run

### 1. Run All Tests
Execute the default Maven test cycle. This triggers the `TestRunner` class.
```bash
mvn clean test
```
### 2. Run Specific Features
You can override the runner options from the command line:

```
mvn test -Dcucumber.features="src/test/resources/featureFiles/{{featureName}}.feature"
```

## Reporting
The framework is configured to generate reports automatically after every run.

HTML Report: 
```target/cucumber-reports/cucumber.html```

JSON Report: 
```target/cucumber-reports/cucumber.json```

## AI Usage Disclosure
**Transparency Note**: This project was developed with the assistance of an AI Language Model.

<span style="color:#FFFFFF"> No classes or methods were copied from AI generation. All code was manually written, reviewed, and integrated by the author to ensure full understanding and ownership of the logic. </span>

**Role:** The AI functioned as a technical consultant and learning tool.

**Usage:**

* Debugging Maven configuration conflicts
* Structure planning
* Assistance with implementation of TestContext class
* Creating documentation templates
* Troubleshooting

