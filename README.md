# Overview

This project demonstrates a full-fledged API automation framework for the Restful Booker API
 using Java, Rest Assured, Cucumber BDD, and TestNG. https://restful-booker.herokuapp.com/apidoc/index.html

 # Execution Flow Diagram
<img width="740" height="970" alt="Untitled Diagram drawio (3)" src="https://github.com/user-attachments/assets/a9983294-df46-40ff-9199-5096d8224e3f" />

# Key features:

1. Each scenario Create its own data, Validate it and Clean it up

2. Reusable RequestSpecification and ResponseSpecification

3. Scenario isolation via TestContext and PicoContainer

4. Token-based authentication with TokenManager (generated once per test run)

5. Payload serialization/deserialization using POJO classes

6. Data-driven testing with JSON files and Scenario Outlines

7. Generic and payload-specific validations using ResponseSpecification and BookingAssertions

8. Logging via AllureRestAssured

9. reporting with Allure Reports
    
10.  CI/CD ready with GitHub Actions

# Best Practices / What NOT to Do

Avoid doing the following inside @Before or step definitions:

❌ Generate token in step definitions — Token is generated once per test run in @Before using TokenManager and reused by all tests.

❌ Build RequestSpecification repeatedly — use RequestFactory for reusable specs

❌ Share booking IDs across scenarios — each scenario creates and cleans its own data

❌ Depend on execution order — all scenarios are self-contained and independent

# Project Structure

<img width="496" height="663" alt="image" src="https://github.com/user-attachments/assets/ec069902-8fd8-458f-9895-f1d9cc0c9949" />

# Getting Started

Prerequisites:

Java 15+

Maven 3.6+

IDE (Eclipse, IntelliJ, etc.)

# Setup

1. Clone the repository:

git clone https://github.com/srividya-sundaravadivelu/RestfulBooker_API_Automation_RestAssured_Cucumber_Testng.git


2. Navigate to project folder:

cd RestfulBooker_API_Automation_RestAssured_Cucumber_Testng


3. Install dependencies:

mvn clean install

# Running Tests
Run all tests:

mvn clean test

Run a single feature:

mvn clean test -Dcucumber.filter.tags="@getBookingIds"


# Reports
Allure Reports

Generate report after test execution:

allure serve target/allure-results

<img width="1919" height="914" alt="image" src="https://github.com/user-attachments/assets/de1d011a-3407-4eb3-83af-d54b956e8845" />

<img width="1919" height="921" alt="image" src="https://github.com/user-attachments/assets/1b87cb8d-e7b6-436e-8536-13bea23a1b1b" />

<img width="1877" height="916" alt="image" src="https://github.com/user-attachments/assets/65453c62-663f-462e-9a99-ae5e9f499335" />

<img width="1915" height="919" alt="image" src="https://github.com/user-attachments/assets/bb2fcae2-549d-49a2-8ce9-df909bc576fc" />


# Test Design Highlights

RequestFactory: Centralized creation of RequestSpecification for all API calls

ResponseSpecBuilderUtil: Generic response validation

BookingAssertions: Payload-specific assertions to avoid duplication

TestContext + PicoContainer: Scenario-level data sharing for Given/When/Then

Hooks.java: @Before → setup token; @After → delete created bookings

Data-driven: Scenario Outlines with JSON payloads for positive and negative tests


# CI/CD Integration

GitHub Actions workflow configured to run API tests on push




