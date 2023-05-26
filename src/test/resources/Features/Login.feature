#Selenium Behavior Driven-Development Example
#Author: Jhonatan Pereira

Feature: Login Functionality
  In order to test the login functionality of "automationpractice.com", the following aspects will be considered:
  -Validate common flow (login/logout).
  -Proper validations of "Full Name/E-Mail" and "Password" fields.
  -"Forgot your password?" button functionality.

  Background:
    Given user is at the Login Page
    And user can observe login fields and login button

  @ExpectedSuccessfulLoginFlow
  Scenario Outline: Login with valid credentials
    When the user inserts a "<username>" into the username textbox
    And the user inserts a "<password>" into the password textbox
    And the user clicks on LOGIN button
    Then the user should navigate to the Profile Page

    Examples:
      | username  | password  |
      | jhonatan.pereira@dtidigital.com.br | aCiKgcro  |

  @ExpectedSuccessfulLogoutFlow
  Scenario Outline: Logout
    Given user has valid credentials: "<username>" and "<password>" and is logged in
    And the user clicks on LOGIN button
    When the user clicks on LOGOUT button
    Then the user should navigate to Login Page

    Examples:
      | username  | password  |
      | jhonatan.pereira@dtidigital.com.br | aCiKgcro  |

  @ExpectedUnsuccessfulLoginFlow
  Scenario Outline: Login with invalid credentials
    When the user inserts a "<username>" into the username textbox
    And the user inserts a "<password>" into the password textbox
    And the user clicks on LOGIN button
    Then system displays an error message indicating incorrect credentials

    Examples:
      | username        | password |
      | invalid@login.com | Invalidpass     |



  @ExpectedSuccessfullRequestLostPasswordFunction
  Scenario: Forgot Password
    Given user is at the Login Page
    And user clicks on FORGOT YOUR PASSWORD button
    Then user navigate to lost password page
    And user insert valid credential: "jhonatan.pereira@dtidigital.com.br"
    And user clicks on RETRIEVE NEW PASSWORD
    Then the system displays a recover password message