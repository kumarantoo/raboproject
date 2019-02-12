<b>Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.</b>

Module name :
<b>CustomerStatementJob</b>

<b>To run the spring boot application please follow the below methods.</b>

1.Install maven 3 and above <br/>
2.Install JDK1.8<br/>
3.Install GIT<br/>
4.Clone Repository using git clone <URL>
 
<b>Commands to execute :</b>
1.mvn clean install<br>
This command will execute all the test cases with date files, which is already placed under resources folder for both xml and csv file format reports.

2.To excute the jar :
java -jar <jar-file-location> reports.xml xml
  
Command line arugments first arguemnt should contain <filename> and second argument should contain file format (xml or csv).

Final report will be generated based on data validation , which is placed under resources/reports.txt


<b>Code Execution with IDE</b>

To execute the application in any IDE please follow the below steps:

1.Import the poject and maven project.<br/>
2.Please make sure you have JDK8 and above in your build path.<br/>
3.Execute clean install once.<br/>
4.Execute the main file <b>CustomerJobMain.java</b>

<B>Test case execution<b>
 To execute the JUNIT test cases please follow the below method<br/>
 1.Execute the test class CustomerJobMain.java 
 2.Place all the data files under src/test/resources folder
