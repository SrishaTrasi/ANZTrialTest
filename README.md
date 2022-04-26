# ANZTrialTest
# ExerciseTest

The Exercise tests has been developed as a maven project with BDD Framework(Cucumber) and used Java as the coding language+ Selenium WebDriver as the automation test tool. For now this been written to run on chrome browser.

High level framework structure is as follows:
Feature File: src/test/resources/Features/Loans/
StepDef File: src/test/java/StepDefs/
RunnerFile: src/test/java/runners/
Driver class, page object class, common utilty class : src/main/java/
Config File: src/test/resources/

The project has two Cucumber Scenarios of which:
1. First scenario covers the first 2 exercises given in the Test
2. Second scenario covers the 3rd exercise given in the Test

#To build and run the tests use the following maven commands:
**mvn clean test**
#Test results can be found in project path **target/cucumber-reports ** and it will create two reports one is a html report and the other is a json file which can be used to import to Jira
