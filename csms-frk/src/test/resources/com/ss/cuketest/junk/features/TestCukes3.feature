#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Feature 3
  I want to use this template for my feature file

  @tag
  Scenario: Scenario3
    Given user navigates to login page
    And user logs in as "foo" "bar"
    When some page is available    
    Then user logs off
    

  @last
  Scenario: Scenario4
    Given user navigates to login page
    And user logs in as "foo" "bar"
    When some page is available    
    Then user logs off
    
    #@tag
  #Scenario: Scenario5
    #Given user navigates to login page
    #And user logs in as "foo" "bar"
    #When some page is available    
    #Then user logs off
    #
    #@tag
  #Scenario: Scenario6
    #Given user navigates to login page
    #And user logs in as "foo" "bar"
    #When some page is available    
    #Then user logs off
    #
    #@tag
  #Scenario: Scenario7
    #Given user navigates to login page
    #And user logs in as "foo" "bar"
    #When some page is available    
    #Then user logs off
    #
    #@tag
  #Scenario: Scenario8
    #Given user navigates to login page
    #And user logs in as "foo" "bar"
    #When some page is available    
    #Then user logs off