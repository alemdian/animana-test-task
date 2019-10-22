Feature: Search
  As a user
  I want to search by patient name
  So that I can see information about specific patient

  Scenario: New Contact is present on the page
    Given I am logged in user
    When I am on the main page
    Then I see new contact icon

  Scenario: Search by patient name
    Given I am on the main page
    And I am selecting Patient from the search drop down
    When I am typing 'Diensthond' in the search bar
    And I click on the Search button
    Then I see patient with below details in search result:
#    Examples:
      | Gender | Name       | Species | Breed           | Age | Owner                                                 | Address    | City     | StartDate  |
      | male   | Diensthond | Canine  | German Shepherd |     | Politie Tav de Dienst Financien, afdeling crediteuren | Bertram 36 | Uithoorn | 09-03-2017 |


