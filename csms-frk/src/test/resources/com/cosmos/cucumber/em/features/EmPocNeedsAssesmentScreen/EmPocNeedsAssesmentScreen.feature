Feature: Needs Assessments pof
  Just a few test cases for a framework demo

  @tmsLink=EM-4612
  Scenario: EM-4612 - pass
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
    And User adds next data to Needs Assessent
      | Title           | AT title demo         |
      | Description     | AT Description Demo   |
      | Justification   | AT Justification demo |
      | Effective Date  | 2018-08-01            |
      | Expiration Date | 2019-08-01            |
      | Year            |                  2018 |
    And User selects next Business Units
      | Bone Cement |
      | Biologics   |
    When User saves Needs Assessment
    Then Needs Assessment shows
    And Needs Assessment is filled with next data
      | Title           | AT title demo          |
      | Description     | AT Description Demo    |
      | Justification   | AT Justification demo  |
      | Effective Date  | 2018-08-01             |
      | Expiration Date | 2019-08-01             |
      | Year            |                   2018 |
      | Business Units  | Biologics, Bone Cement |
      | Original ID     |                      0 |
      | Region          | Americas               |
      | Functional Area | Advisory Group         |
    When User adds next Business Units
      | Dental |
      | Hip    |
    And User saves Needs Assessment
    Then Needs Assessment shows
    And Business Units has next items selected
      | Bone Cement |
      | Biologics   |
      | Dental      |
      | Hip         |
    And Needs Assessments Editor looks like "EM-4612_expected_na_editor.png"		

		
		
  Scenario: EM-4612 - validation failure
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
    And User adds next data to Needs Assessent
      | Title           | AT title demo         |
      | Description     | AT Description Demo   |
      | Justification   | AT Justification demo |
      | Effective Date  | 2018-08-01            |
      | Expiration Date | 2019-08-01            |
      | Year            |                  2018 |
    And User selects next Business Units
      | Bone Cement |
      | Biologics   |
    When User saves Needs Assessment
    Then Needs Assessment shows
    And Needs Assessment is filled with next data
      | Title           | AT title demo 1         |
      | Description     | AT Description Demo 2   |
      | Justification   | AT Justification demo 3 |
      | Effective Date  | 2018-08-01              |
      | Expiration Date | 2019-09-01              |
      | Year            |                    2018 |
      | Business Units  | Biologics, Bone Cement2 |
      | Original ID     |                       0 |
      | Region          | Americas                |
      | Functional Area | Advisory Group          |

  Scenario: EM-4612 - screenshot comparison failure
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
    And User adds next data to Needs Assessent
      | Title           | AT title demo         |
      | Description     | AT Description Demo   |
      | Justification   | AT Justification demo |
      | Effective Date  | 2018-08-01            |
      | Expiration Date | 2019-08-01            |
      | Year            |                  2018 |
    And User selects next Business Units
      | Bone Cement |
      | Biologics   |
    When User saves Needs Assessment
    Then Needs Assessment shows    
    When User adds next Business Units
      | Dental |
      | Hip    |
    And User saves Needs Assessment
    Then Needs Assessment shows   
    And Needs Assessments Editor looks like "EM-4612_expected_na_editor_bad.png"
