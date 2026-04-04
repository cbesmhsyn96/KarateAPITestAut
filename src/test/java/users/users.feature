Feature: GoRest Kullanıcı Yönetimi API Testleri

  Background:
    * url apiUrl
    * def generator = call read('classpath:helpers/token-generator.js')

    * def userPayload =
    """
    {
      "name": "#(generator.name)",
      "gender": "male",
      "email": "#(generator.email)",
      "status": "active"
    }
    """

  @smoke
  Scenario: Yeni bir kullanıcı oluştur ve ID ile sorgula (POST & GET)
    Given path 'users'
    And request userPayload
    When method post
    Then status 201
    * def actualResponse = env == 'dev' ? response.data : response
    And match actualResponse.name == generator.name
    * def createdUserId = actualResponse.id

    Given path 'users', createdUserId
    When method get
    Then status 200
    * def getResponse = env == 'dev' ? response.data : response
    And match getResponse.id == createdUserId
    And match getResponse.email == generator.email

  @negative
  Scenario: Geçersiz verilerle kullanıcı oluşturma hatası (Data Validation)
    Given path 'users'
    And request { "name": "", "email": "invalid-email" }
    When method post
    Then status 422

    * def errorList = env == 'dev' ? response.data : response

    And match errorList contains deep { field: 'email', message: 'is invalid' }
    And match errorList contains deep { field: 'name', message: "can't be blank" }