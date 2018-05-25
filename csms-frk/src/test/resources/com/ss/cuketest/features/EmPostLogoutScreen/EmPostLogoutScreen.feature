Feature: EM PostLogout Screen
 EM PostLogout Screen related tests

  Scenario: Login Logout GoBack
    Given user navigates to login page
    When user inputs "admin@alpha.com" as login and "password1" as password
    And clicks Login button
    Then Dashboard page shows    
    Then user logs off from Dashboard     
    Then PostLogout page shows
    And PostLogout page looks like "post_logout.png"
    When user clicks Go Back button
    Then LoginPage shows

  Scenario: Login Logout Home
    Given user navigates to login page
    When user inputs "admin@alpha.com" as login and "password1" as password
    And clicks Login button
    Then Dashboard page shows       
    Then user logs off from Dashboard
    Then PostLogout page shows
    And PostLogout page looks like "post_logout.png"     
    When user clicks Home button
    Then LoginPage shows

  #Scenario: Login Logout LoginScreen
    #Given user navigates to login page
    #When user inputs "admin@alpha.com" as login and "password1" as password
    #And clicks Login button
    #Then Dashboard page shows
    #Then user logs off from Dashboard
    #Then PostLogout page shows
    #When user clicks Login Screen button
    #Then LoginPage shows
