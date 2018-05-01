# Carbon-QueryTracker
Project to Monitor and Trace Carbon Queries

git clone git@github.com:BJangir/Carbon-QueryTracker.git

To start:-
mvn jetty:run  
(this will start Jetty Server also)

For Debug
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"

mvn jetty:run

To Form a WAR(Web Application Archive ) run below command
mvn package

For Spark JDBC 
URL:-jdbc:hive2://94.130.66.55:10001
Driver Class=org.apache.hive.jdbc.HiveDriver

Open Issues:-
1. Query Result willbe displayed in the Next Page, it should display in current page
2. Connecton should be presist , currently it restarted jetty then connection is gone
3. Query Inprogress to be displayed, Progress Bar (currently show 100% )


To be Enhanced for
 1.Setup and JDBCBased Connection (presist it for next time)
 2.Run SQL Command
 3. View Master and Worker  status
 4. Enable Search Mode
 5. Disable search Mode
 6. Input split status //
 7. Query Progress monitoring
 8. Concurrent Query Execution
 9. Refersh button to get intermidiat status