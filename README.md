Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

###To run the application please follow the below methods.
To run application as executable jar:

1.Install maven 3 and above
2.Install JDK1.8
3.Install GIT
4.Clone Repository using git clone <URL>
 
###Commands to execute :
1.mvn clean install<br>
This command will execute all the test cases with date files, which is already placed under resources folder for both xml and csv file format reports.

2.To excute the jar :
java -jar <jar-file-location> reports.xml xml
  
###Command line arugments first arguemnt should contain filename and second argument should contain file format (xml or csv).

###Final report will be generated based on data validation , which is placed under resources/reports.txt
