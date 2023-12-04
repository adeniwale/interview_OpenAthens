Feature: OpenAthens Website Registration

  Background:
    Given I am on the OpenAthens Home webpage
    And I navigate to the OpenAthens Registration webpage

  @FrontendTest
  Scenario Outline: A student can successfully register with OpenAthens website
    When I register the student details - '<firstname>', '<email>', '<web>', '<interests>', '<password>' and '<confirm>'
    Then a successful registration for '<firstname>' is displayed on the OpenAthens Dashboard webpage

    Examples:
    | firstname | email                | web                     | interests | password | confirm  |
    | victor    | vickky@myemail.co.uk | http://www.vickky.co.uk | computing | oawsreg1 | oawsreg1 |

  @FrontendTest
  Scenario Outline: Invalid email and confirm password
    When I register the student details - '<firstName>', '<email>', '<web>', '<interests>', '<password>' and '<confirm>'
    Then an email validation error message 'You must provide a valid email address' is displayed on the OpenAthens Registration webpage
    And a confirm password validation error message 'Your passwords did not match' is displayed on the OpenAthens Registration webpage

    Examples:
      | firstName | email          | web                     | interests | password | confirm  |
      | victor    | vickky_myemail | http://www.vickky.co.uk | computing | oawsreg1 |          |

  @FrontendTest
  Scenario Outline: Invalid password and confirm password
    When I register the student details - '<firstname>', '<email>', '<web>', '<interests>', '<password>' and '<confirm>'
    Then a password validation error message 'Your password must be longer than 8 characters' is displayed on the OpenAthens Registration webpage
    And a confirm password validation error message 'Your passwords did not match' is displayed on the OpenAthens Registration webpage

    Examples:
      | firstname | email                | web                     | interests | password | confirm  |
      | victor    | vickky@myemail.co.uk | http://www.vickky.co.uk | computing | oaws1    |          |

  # Possible steps for Task 2b
  #@BackendTest
  #Scenario: successfully register student with OpenAthens API
    #Given I am using OpenAthens API
    #When I call POST API for Registration request
    #Then the response status is 200