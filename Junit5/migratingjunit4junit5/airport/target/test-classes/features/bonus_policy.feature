Feature: Bonus Policy
  The company follows a bonus policy, depending on the passenger type and on the distance that has been traveled

  Scenario Outline: Passenger bonus policy
    Given we have the flights defined into "<file1>" and "<file2>" and "<file3>"
    When the passengers travel according to the data recorded into these files
    Then passenger with identifier "<identifier>" name "<name>" and countryCode "<countryCode>" has been awarded <points> points

    Examples:
      | file1                       | file2                    | file3                    | identifier                  | name                   | countryCode | points                   |
      | flights_information.csv     | flights_information2.csv | flights_information3.csv | 900-45-6809                 | Susan Todd             | GB          | 210                      |
      | flights_information.csv     | flights_information2.csv | flights_information3.csv | 900-45-6797                 | Harry Christensen      | GB          | 420                      |
      | flights_information.csv     | flights_information2.csv | flights_information3.csv | 123-45-6799                 | Bethany King           | US          | 630                      |
