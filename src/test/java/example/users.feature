Feature: User API

  Scenario: List all users
    Given url 'https://jsonplaceholder.typicode.com'
    And path 'users'
    When method get
    Then status 200
    # Verify we got an array of 10 users
    And match response == '#[10]'
    # Each user has the expected shape
    And match each response contains { id: '#number', name: '#string', email: '#string' }

  Scenario: Get a single user
    Given url 'https://jsonplaceholder.typicode.com'
    And path 'users', 1
    When method get
    Then status 200
    And match response.name == 'Leanne Graham'
    And match response.address.city == '#string'

  Scenario: Create a user
    Given url 'https://jsonplaceholder.typicode.com'
    And path 'users'
    And request { name: 'Jane Doe', email: 'jane@example.com' }
    When method post
    Then status 201
    And match response contains { name: 'Jane Doe' }