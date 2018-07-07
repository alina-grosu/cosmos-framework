Feature: Needs Assessments pof
  Just a few test cases for a framework demo

  Scenario: EM-4612 NA - User can add new NA with multiple Business Unit values
    Given User logs as "admin@medispend.com" with password "password1"
    Then Dashboard page shows
    When User navigates to "Users, Groups and Permissions" view
    Then Users, Groups and Permissions view shows
    When User cloaks as role "EM Client Admin"
    Then Dashboard page shows
    When user selects Engagement Manager
    Then Engagement Manager shows
    When User initiates new Needs Assessment
    Then Needs Assessment shows
    #And User picks Effective Date as "2018-08-01"
    #And User picks Expiration Date as "2019-08-01"
    And User add next data to Needs Assessent
      | Title           | AT title demo         |
      | Description     | AT Description Demo   |
      | Justification   | AT Justification demo |
      | Effective Date  | 2018-08-01            |
      | Expiration Date | 2019-08-01            |
      | Year            | 2018                  |
    And User selects next Bussiness Units
      | Biologics   |
      | Bone Cement |
    Then wait for "30000" 
