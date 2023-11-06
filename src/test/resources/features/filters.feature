Feature: Apply filters

  Scenario Outline: Launch Name and Failed filters
    Given User is logged in
    And Launches page is opened
    And Filters section is opened
    When User select Launch Name filter as <launchName>
    And User add Failed filter and enter <filterInput>
    Then Launches list refreshes and displays <launchesAmountOutput> launches that contains <launchName>

    Examples:
      | launchName | filterInput | launchesAmountOutput |
      | Demo       | 1           | 4                    |
      | Demo       | 6           | 3                    |
      | Demo       | 8           | 3                    |
      | Demo       | 9           | 2                    |

  Scenario Outline: Launch Name and Skipped filters
    Given User is logged in
    And Launches page is opened
    And Filters section is opened
    When User select Launch Name filter as <launchName>
    And User add Skipped filter and enter <filterInput>
    Then Launches list refreshes and displays <launchesAmountOutput> launches that contains <launchName>

    Examples:
      | launchName | filterInput | launchesAmountOutput |
      | Demo       | 0           | 5                    |
      | Demo       | 1           | 2                    |
      | Demo       | 2           | 1                    |
      | Demo       | 3           | 0                    |

  Scenario Outline: Launch Name and Product bug filters
    Given User is logged in
    And Launches page is opened
    And Filters section is opened
    When User select Launch Name filter as <launchName>
    And User add Product bug filter and enter <filterInput>
    Then Launches list refreshes and displays <launchesAmountOutput> launches that contains <launchName>

    Examples:
      | launchName | filterInput | launchesAmountOutput |
      | Demo       | 0           | 5                    |
      | Demo       | 1           | 3                    |
      | Demo       | 4           | 2                    |
      | Demo       | 5           | 0                    |