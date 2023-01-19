**What are we testing**

**we are mainly testing the below 2 scenarios :**

**Scenario 1** : Test to create a Time Entry and submit. Verify that item entry is submitted and has status as “Submitted”.

**Scenario 2** : Test to approve the time entry created in Scenario-1. The time entry should be verified to have status as “Approved”.
Instructions on how to run the tests:
Prerequisites:

The code is Cross Browser and can run for firefox/edge/chrome. Currently its running for chrome. So Chrome should be installed.(to make it run for other browsers, respective browser name should be passed from testng.xml runner file.)
Java and maven should be installed.

Extent Report & Allure report is also implemented. To view them, create below folders inside the top project folder.
suppose you have cloned it to c drive in your local machine . then create folders as :

      C:\ proMXSeleniumFramework\allure-results
      C:\ proMXSeleniumFramework\ExtentReports
      C:\ proMXSeleniumFramework\screenshot
      
To view allure reporting, allure should be installed.

Steps:
1.	Clone the repo in your local machine

2.	Open command prompt

3.	Change the directory to the path where the project has been cloned
suppose you have cloned it to c drive in your local machine the a command like below has to be written:
cd C:\ proMXSeleniumFramework
After the directory gets changed, run the command:

    mvn clean install

4.	And that is it, it should run all the 3 test cases which I have written.

5.	After the test cases are executed , you should be able to view the test testng report in the below path: proMXSeleniumFramework\test-output\index.html
Extent report will be at proMXSeleniumFramework\ExtentReports\TestExecutionReport.html
For allure report, from the above cmd prompt, run below command

      allure serve allure-results
      
Allure report should open.

