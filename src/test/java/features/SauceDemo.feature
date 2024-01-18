Feature: SauceDemo

    #TODO Use Standard_user to order one item and checkout, check if after clicking Back Home it is working properly
    #TODO Use assertion when needed
  @SU_One_Item
  Scenario: Standard user Orders One Item
    Given SauceDemo is opened
    Then I login as standard user
    Then I add product to cart
    And I do checkout
    Then I fill info
    And I return to home page
    Then I check if homepage valid



    #TODO Use problem_user to order one item and checkout, check if after clicking Back Home it is working properly
    #TODO Use assertion when needed
  @PU_One_Item
  Scenario: Problem user Orders One Item
    Given SauceDemo is opened
    Then I login as problem user
    Then I add product to cart
    And I do checkout
    Then I fill info
    And I return to home page
    Then I check if homepage valid
    #TODO Use performance_glitch_user to order one item and checkout, check if after clicking Back Home it is working properly
    #TODO Use assertion when needed
  @PGU_One_Item
  Scenario: Performance glitch user Orders One Item
    Given SauceDemo is opened
    Then I login as performance glitch user
    Then I add product to cart
    And I do checkout
    Then I fill info
    And I return to home page
    Then I check if homepage valid






