
Feature: Login Screen
  EM Login Screen related tests

  
  Scenario: Successfull login
    Given user navigates to login page
    When user inputs "admin@alpha.com" as login and "password1" as password
    And clicks Login button
    Then Dashboard page shows    
    Then user logs off from Dashboard
    Then PostLogout page shows
    When user clicks Login Screen button
    Then LoginPage shows
    
  Scenario: Unsuccessfull login
    Given user navigates to login page
    When user inputs "admin@alpha.com" as login and "password2" as password
    And clicks Login button
    Then error with text "WRONG EMAIL OR PASSWORD." appears    
    And LoginPage shows    