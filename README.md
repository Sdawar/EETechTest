# Testing Notes

This test is written in java to test hotel booking html form, feature tested in this project are:
 - Add booking
 - Delete booking


## Running the Tests

To run the tests, navigate to project root, for first time run, use below command in terminal

`mvn clean install`

all the subsequent run can be initiated by:

`mvn test`

Above will run the test and create target directory in test directory.

Successful Test would return similar to below result:
```
  [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
  [INFO] 
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
```
## Screenshot:
Screenshots of the tests will be saved in target folder under cucumber-reports. 
Screenshots will be generated once the tests finished running.

## Reports:
Reports of the tests will be generated in taregt folder under cucumber-reports and inside html-reports.
Open the file .html in the browser of your choice.
