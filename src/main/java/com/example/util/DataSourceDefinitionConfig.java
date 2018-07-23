package com.example.util;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;

@Singleton
/*@DataSourceDefinition(
		name="java:comp/DefaultDataSource",
		className="org.h2.Driver",
		url="jdbc:h2:tcp://localhost/~/student;MODE=Oracle;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE",
		user="sa",
		password="sa",
		initialPoolSize=2,
		minPoolSize=3,
		maxPoolSize=4,
		properties= {"TestOnBorrow=true", "TestWhileIdle=true"}
)*/
public class DataSourceDefinitionConfig {

}
