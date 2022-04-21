Feature: List of tests around personal home loan estimates

  @SmokeTest
  Scenario: Verify the borrow estimate calculator
    Given I have landed on the personal home loan page
    When I select below values in "Your details" for borrow estimate
      | Application type | Number of dependants | Property you would like to buy |
      | Single           | 0                    | Home to live in                |
    And I select below values in "Your earnings" for borrow estimate
      | Your annual income (before tax) | Your annual other income (optional) |
      | 80000                           | 10000                               |
    And I select below values in "Your expenses" for borrow estimate
      | Monthly living expenses | Current home loan monthly repayments | Other loan monthly repayments | Other monthly commitments | Total credit card limits |
      | 500                     | 0                                    | 100                           | 0                         | 10000                    |
    Then I should see the estimate as "$542,000"
    When I click on Start Over
    Then I should see all the fields been reset to zero

  Scenario: Verify the error in borrow estimate calculator
    Given I have landed on the personal home loan page
    When I select below values in "Your expenses" for borrow estimate
      | Monthly living expenses |
      | 1                       |
    And I click on calculate estimate
    Then I can verify an informative message is displayed

