Technology used:
Selenium for UI
RestAssured for APIs
Maven
Java
TestNG for Test management
ExtendReports (Reporting)

-Just execute the command for execute selenium and API tests
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testsuite/WebTestSuite.xml,src/test/resources/testsuite/APITestSuite.xml