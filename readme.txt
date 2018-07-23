mvn clean package tomee:run -Dmaven.test.skip
mvn -f tomee-embedded-shade.xml clean package -Dmaven.test.skip
mvn clean test

hit the url http://localhost:8080/api/hello

java -Dorg.tomitribe.sabot.environment=prod 
	 -DDefaualtDataSource=new://Resource?type=javax.sql.DataSource 
	 -DDefaualtDataSource.jdbcDriver=org.h2.Driver 
	 -DDefaualtDataSource.jdbcUrl="jdbc:h2:tcp://localhost/~/student;MODE=Oracle;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE" 
	 -DDefaualtDataSource.username=sa 
	 -DDefaualtDataSource.password=sa 
	 -jar  
	 tomeedemo.jar  
	 --as-war
	 
java -Dorg.tomitribe.sabot.environment=prod -DDefaualtDataSource=new://Resource?type=javax.sql.DataSource -DDefaualtDataSource.jdbcDriver=org.h2.Driver -DDefaualtDataSource.jdbcUrl="jdbc:h2:tcp://localhost/~/student;MODE=Oracle;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE" -DDefaualtDataSource.username=sa -DDefaualtDataSource.password=sa  -jar tomeedemo.jar  --as-war
-javaagent:openejb-javaagent.jar	 